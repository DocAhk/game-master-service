package application;

import logic.Dice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;

@CrossOrigin(origins = "${cross.origin}")
@RestController
public class Controller {

    private Dice die = new Dice(20);

    @GetMapping(path="/diceRoll")
    public int diceRoll(@RequestParam(value="sides", defaultValue="20") int sides) {
        die.setSides(sides);
        return die.roll();
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam(value="portraitFileName") String portraitFileName) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/assets/portraits/" + portraitFileName);
        return IOUtils.toByteArray(in);
    }
}
