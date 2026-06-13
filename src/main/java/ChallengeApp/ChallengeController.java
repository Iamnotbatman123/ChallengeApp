package ChallengeApp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {

    private ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(),HttpStatus.OK);
    }

    /*@GetMapping("/challenges/{id}")
    public Challenge getChallengeById(@PathVariable Long id){
        return challengeService.getChallengeById(id);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id){
        Challenge challenge = challengeService.getChallengeById(id);
        
        if (challenge != null){
            return new ResponseEntity<>(challenge, HttpStatus.OK);}
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge);

        if (isChallengeAdded){
            return new ResponseEntity<>("Challenge added successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Challenge not added successfully",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        Boolean isChallengeUpdated = challengeService.updateChallenge(id,updatedChallenge);

        if (isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        }

        else{
            return new ResponseEntity<>("Challenge not updated successfully", HttpStatus.NOT_FOUND);
        }

        
    }

    // Delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {

        Boolean isChallengeDeleted = challengeService.deleteChallenge(id);

        if (isChallengeDeleted){
            return new ResponseEntity<>("Challenge deleted successfully",HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("Challenge not deleted successfully",HttpStatus.NOT_FOUND);
        }
    }

}
