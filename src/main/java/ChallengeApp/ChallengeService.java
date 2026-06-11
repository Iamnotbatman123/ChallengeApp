package ChallengeApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    /*private List<Challenge> challenges = new ArrayList<>();
    /*private static Long nextId = 0L; // starting id i think*/
    @Autowired
    private ChallengeRepository challengeRepository;

    public ChallengeService() {
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Challenge getChallengeById(Long id){
        Optional<Challenge> challenge = challengeRepository.findById(id);
        return challenge.orElse(null);

    }

    public boolean addChallenge(Challenge challenge) {
        if (challenge != null){
            challengeRepository.save(challenge);
            return true;
        }
        
        return false;
    }

    /*public boolean addChallenge(Challenge challenge) {
        if (challenge == null) {
            return false;
        }
        challengeRepository.save(challenge);
        return true;
    }*/

    public boolean updateChallenge(Long id, Challenge updateChallenge) {
        
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if(challenge.isPresent()) {
            Challenge updatingChallenge = challenge.get();
            updatingChallenge.setMonth(updateChallenge.getMonth());
            updatingChallenge.setDescription(updateChallenge.getDescription());
            challengeRepository.save(updatingChallenge);
            return true;
        }
            
            return false;
        
    }

    public boolean deleteChallenge(Long id) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    

    

}
