const Voting = artifacts.require("Voting");

contract("Voting", (accounts) => {
  let votingInstance;

  beforeEach(async () => {
    votingInstance = await Voting.new();
  });

  it("should create an election", async () => {
    await votingInstance.createElection("Election 1", "2022-01-01", "2022-12-31");
    const election = await votingInstance.elections(1);
    
    assert.equal(election.id, 1, "Election ID should be 1");
    assert.equal(election.name, "Election 1", "Election name should be 'Election 1'");
    assert.equal(election.startDate, "2022-01-01", "Election start date should be '2022-01-01'");
    assert.equal(election.endDate, "2022-12-31", "Election end date should be '2022-12-31'");
  });

  it("should add a candidate to the election", async () => {
    await votingInstance.createElection("Election 1", "2022-01-01", "2022-12-31");
    await votingInstance.addCandidate(1, "Candidate 1");
    
    const candidate = await votingInstance.getCandidate(1, 1);
    
    assert.equal(candidate[0], 1, "Candidate ID should be 1");
    assert.equal(candidate[1], "Candidate 1", "Candidate name should be 'Candidate 1'");
    assert.equal(candidate[2], 0, "Candidate vote count should be 0");
  });

  it("should allow a user to vote", async () => {
    await votingInstance.createElection("Election 1", "2022-01-01", "2022-12-31");
    await votingInstance.addCandidate(1, "Candidate 1");
    
    await votingInstance.vote(1, 1, { from: accounts[0] });
    
    const candidate = await votingInstance.getCandidate(1, 1);
    const hasVoted = await votingInstance.hasVoted(1, { from: accounts[0] });

    assert.equal(candidate[2], 1, "Candidate vote count should be 1");
    assert.equal(hasVoted, true, "User should have voted");
  });

  it("should not allow a user to vote twice", async () => {
    await votingInstance.createElection("Election 1", "2022-01-01", "2022-12-31");
    await votingInstance.addCandidate(1, "Candidate 1");

    await votingInstance.vote(1, 1, { from: accounts[0] });

    try {
      await votingInstance.vote(1, 1, { from: accounts[0] });
      assert.fail("User was able to vote twice");
    } catch (error) {
      assert(error.message.includes("Already voted"), "Expected 'Already voted' error");
    }
  });
});
