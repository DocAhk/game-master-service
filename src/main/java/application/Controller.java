package application;

import character.Stat;
import logic.Dice;
import character.Character;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
@RestController
public class Controller {

    private Dice die = new Dice(20);

    @GetMapping(path="/diceRoll")
    public int diceRoll(@RequestParam(value="sides", defaultValue="20") int sides) {
        die.setSides(sides);
        return die.roll();
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam(value="portraitFileName") String portraitFileName) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/assets/portraits/" + portraitFileName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping(path="/Jim")
    public Character getJim() {
        Character jim = new Character();
        jim.setFirstName("Jim");
        jim.setPortrait("male_aumaua_b_lg.png");
        jim.getStats().add(new Stat("Agility", 5));
        jim.getStats().add(new Stat("Charisma", 10));
        jim.getStats().add(new Stat("Constitution", 15));
        jim.getStats().add(new Stat("Intelligence", 10));
        jim.getStats().add(new Stat("Mana", 0));
        jim.getStats().add(new Stat("Perception", 10));
        jim.getStats().add(new Stat("Strength", 20));
        return jim;
    }
}
