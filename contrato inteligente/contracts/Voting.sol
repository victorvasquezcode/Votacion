// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

contract Voting {
    struct Candidate {
        uint id;
        string name;
        uint voteCount;
    }

    struct Election {
        uint id;
        string name;
        string startDate;
        string endDate;
        uint candidateCount;
        mapping(uint => Candidate) candidates;
        mapping(address => bool) voters;
    }

    mapping(uint => Election) public elections;
    uint public electionCount;

    event ElectionCreated(uint indexed electionId, string name, string startDate, string endDate);
    event CandidateAdded(uint indexed electionId, uint indexed candidateId, string name);
    event Voted(uint indexed electionId, uint indexed candidateId, address voter);

    function createElection(string memory _name, string memory _startDate, string memory _endDate) public {
        electionCount++;
        Election storage newElection = elections[electionCount];
        newElection.id = electionCount;
        newElection.name = _name;
        newElection.startDate = _startDate;
        newElection.endDate = _endDate;
        
        emit ElectionCreated(electionCount, _name, _startDate, _endDate);
    }

    function addCandidate(uint _electionId, string memory _name) public {
        Election storage election = elections[_electionId];
        election.candidateCount++;
        election.candidates[election.candidateCount] = Candidate(election.candidateCount, _name, 0);

        emit CandidateAdded(_electionId, election.candidateCount, _name);
    }

    function vote(uint _electionId, uint _candidateId) public {
        Election storage election = elections[_electionId];
        require(!election.voters[msg.sender], "Already voted");
        
        election.voters[msg.sender] = true;
        election.candidates[_candidateId].voteCount++;
        
        emit Voted(_electionId, _candidateId, msg.sender);
    }

    function getCandidate(uint _electionId, uint _candidateId) public view returns (uint, string memory, uint) {
        Candidate memory candidate = elections[_electionId].candidates[_candidateId];
        return (candidate.id, candidate.name, candidate.voteCount);
    }

    function hasVoted(uint _electionId) public view returns (bool) {
        return elections[_electionId].voters[msg.sender];
    }
}
