package br.senai.sc.trunfo.init;

import br.senai.sc.trunfo.controller.CardController;
import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.security.enums.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import br.senai.sc.trunfo.controller.UserController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.senai.sc.trunfo.model.dto.UserDTO;
import java.util.List;

@Component
public class StartUpComponent implements CommandLineRunner {
    private CardController cardController;
    private UserController userController;
    @Autowired
    private void setCardController(CardController cardController) {
        this.cardController = cardController;
    }
    @Autowired
    private void setUserController(UserController userController) {
        this.userController = userController;
    }
    @Value("${global.admin.username}")
    private String adminUsername;
    @Value("${global.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        if (!(userController.listLogin(adminUsername, adminPassword).hasBody())) {
            saveCardInit(new CardDTO("SQUIRREL", 0, 1, List.of(SigilsType.NONE), ImageType.SQUIRREL));
            saveCardInit(new CardDTO("STOAT", 1, 3, List.of(SigilsType.NONE), ImageType.STOAT));
            saveCardInit(new CardDTO("STINKBUG", 1, 2, List.of(SigilsType.STINKY), ImageType.STINKBUG));
            saveCardInit(new CardDTO("STUNTED WOLF", 2, 2, List.of(SigilsType.NONE), ImageType.STUNTEDWOLF));
            saveCardInit(new CardDTO("WOLF", 3, 2, List.of(SigilsType.NONE), ImageType.WOLF));
            saveCardInit(new CardDTO("WOLF", 3, 2, List.of(SigilsType.NONE), ImageType.WOLF));
            saveCardInit(new CardDTO("WOLF CUB", 1, 1, List.of(SigilsType.FLEDGELING), ImageType.WOLFCUB));
            saveCardInit(new CardDTO("COYOTE", 2, 1, List.of(SigilsType.NONE), ImageType.COYOTE));
            saveCardInit(new CardDTO("BLOODHOUND", 2, 3, List.of(SigilsType.GUARDIAN), ImageType.BLOODHOUND));
            saveCardInit(new CardDTO("ALPHA", 1, 2, List.of(SigilsType.LEADER), ImageType.ALPHA));
            saveCardInit(new CardDTO("URAYULI", 7, 7, List.of(SigilsType.NONE), ImageType.URAYULI));
            saveCardInit(new CardDTO("GRIZZLY", 4, 6, List.of(SigilsType.NONE), ImageType.GRIZZLY));
            saveCardInit(new CardDTO("GREAT WHITE", 4, 2, List.of(SigilsType.WATERBORNE), ImageType.GREATWHITE));
            saveCardInit(new CardDTO("AMOEBA", 1, 2, List.of(SigilsType.AMORPHOUS), ImageType.AMOEBA));
            saveCardInit(new CardDTO("AMALGAM", 3, 3, List.of(SigilsType.NONE), ImageType.AMALGAM));
            saveCardInit(new CardDTO("MOLE MAN", 0, 6, List.of(SigilsType.MIGHTYLEAP, SigilsType.BURROWER), ImageType.MOLEMAN));
            saveCardInit(new CardDTO("TURKEY VULTURE", 3, 3, List.of(SigilsType.AIRBORNE), ImageType.TURKEYVULTURE));
            saveCardInit(new CardDTO("SPARROW", 1, 2, List.of(SigilsType.AIRBORNE), ImageType.SPARROW));
            saveCardInit(new CardDTO("RAVEN EGG", 0, 2, List.of(SigilsType.FLEDGELING), ImageType.RAVENEGG));
            saveCardInit(new CardDTO("MAGPIE", 1, 1, List.of(SigilsType.AIRBORNE, SigilsType.HOARDER), ImageType.MAGPIE));
            saveCardInit(new CardDTO("KINGFISHER", 1, 1, List.of(SigilsType.WATERBORNE, SigilsType.AIRBORNE), ImageType.KINGFISHER));
            saveCardInit(new CardDTO("RAT KING", 2, 1, List.of(SigilsType.BONEKING), ImageType.RATKING));
            saveCardInit(new CardDTO("FIELD MICE", 2, 2, List.of(SigilsType.FECUNDITY), ImageType.FIELDMICE));
            saveCardInit(new CardDTO("BEAVER", 1, 3, List.of(SigilsType.DAMBUILDER), ImageType.BEAVER));
            saveCardInit(new CardDTO("RABBIT", 0, 1, List.of(SigilsType.NONE), ImageType.RABBIT));
            saveCardInit(new CardDTO("PORCUPINE", 1, 2, List.of(SigilsType.SHARPQUILLS), ImageType.PORCUPINE));
            saveCardInit(new CardDTO("STRANGELARVA", 0, 3, List.of(SigilsType.FLEDGELING), ImageType.STRANGELARVA));
            saveCardInit(new CardDTO("STRANGEPUPA", 0, 3, List.of(SigilsType.FLEDGELING), ImageType.STRANGEPUPA));
            saveCardInit(new CardDTO("MOTHMAN", 7, 3, List.of(SigilsType.AIRBORNE), ImageType.MOTHMAN));
            saveCardInit(new CardDTO("BEE", 1, 1, List.of(SigilsType.AIRBORNE), ImageType.BEE));
            saveCardInit(new CardDTO("RING WORM", 0, 1, List.of(SigilsType.NONE), ImageType.RINGWORM));
            saveCardInit(new CardDTO("ANT QUEEN", 1, 3, List.of(SigilsType.ANTSPAWNER), ImageType.ANTQUEEN));
            saveCardInit(new CardDTO("MANTISGOD", 1, 1, List.of(SigilsType.TRIFURCATEDSTRIKE), ImageType.MANTISGOD));
            saveCardInit(new CardDTO("MANTIS", 1, 1, List.of(SigilsType.BIFURCATEDSTRIKE), ImageType.MANTIS));
            saveCardInit(new CardDTO("WORKER ANT", 1, 2, List.of(SigilsType.NONE), ImageType.WORKERANT));
            saveCardInit(new CardDTO("CORPSE MAGGOTS", 1, 2, List.of(SigilsType.CORPSEEATER), ImageType.CORPSEMAGGOTS));
            saveCardInit(new CardDTO("COCKROACH", 1, 1, List.of(SigilsType.UNKILLABLE), ImageType.COCKROACH));
            saveCardInit(new CardDTO("BEEHIVE", 0, 2, List.of(SigilsType.BEESWITHIN), ImageType.BEEHIVE));
            saveCardInit(new CardDTO("GECK", 1, 1, List.of(SigilsType.NONE), ImageType.GECK));
            saveCardInit(new CardDTO("SKINK", 1, 2, List.of(SigilsType.LOOSETAIL), ImageType.SKINK));
            saveCardInit(new CardDTO("RATTLER", 3, 1, List.of(SigilsType.NONE), ImageType.RATTLER));
            saveCardInit(new CardDTO("ADDER", 1, 1, List.of(SigilsType.TOUCHOFDEATH), ImageType.ADDER));
            saveCardInit(new CardDTO("BULLFROG", 1, 2, List.of(SigilsType.MIGHTYLEAP), ImageType.BULLFROG));
            saveCardInit(new CardDTO("RIVER SNAPPER", 1, 6, List.of(SigilsType.NONE), ImageType.RIVERSNAPPER));
            saveCardInit(new CardDTO("OUROBOROS", 1, 1, List.of(SigilsType.NONE), ImageType.OUROBOROS));
            saveCardInit(new CardDTO("PRONGHORN", 1, 3, List.of(SigilsType.SPRINTER, SigilsType.BIFURCATEDSTRIKE), ImageType.PRONGHORN));
            saveCardInit(new CardDTO("MOOSE BUCK", 3, 7, List.of(SigilsType.HEFTY), ImageType.MOOSEBUCK));
            saveCardInit(new CardDTO("ELK FAWN", 1, 1, List.of(SigilsType.SPRINTER, SigilsType.FLEDGELING), ImageType.ELKFAWN));
            saveCardInit(new CardDTO("ELK", 2, 4, List.of(SigilsType.SPRINTER), ImageType.ELK));
            saveCardInit(new CardDTO("BLACK GOAT", 0, 1, List.of(SigilsType.WORTHYSACRIFICE), ImageType.BLACKGOAT));
            saveCardInit(new CardDTO("CHILD 13", 0, 1, List.of(SigilsType.MANYLIVES), ImageType.CHILD13));
            saveCardInit(new CardDTO("THE SMOKE", 0, 1, List.of(SigilsType.BONEKING), ImageType.THESMOKE));
            saveCardInit(new CardDTO("GREATER SMOKE", 1, 3, List.of(SigilsType.BONEKING), ImageType.GREATERSMOKE));
            saveCardInit(new CardDTO("OPOSSUM", 1, 1, List.of(SigilsType.NONE), ImageType.OPOSSUM));
            saveCardInit(new CardDTO("RIVER OTTER", 1, 1, List.of(SigilsType.WATERBORNE), ImageType.RIVEROTTER));
            saveCardInit(new CardDTO("BAT", 2, 1, List.of(SigilsType.AIRBORNE), ImageType.BAT));
            saveCardInit(new CardDTO("SKUNK", 0, 3, List.of(SigilsType.STINKY), ImageType.SKUNK));
            saveCardInit(new CardDTO("CAT", 0, 1, List.of(SigilsType.MANYLIVES), ImageType.CAT));
            saveCardInit(new CardDTO("UNDEAD CAT", 3, 6, List.of(SigilsType.NONE), ImageType.UNDEADCAT));
            saveCardInit(new CardDTO("MOLE", 0, 4, List.of(SigilsType.BURROWER), ImageType.MOLE));
            saveUserInit(new UserDTO(adminUsername, adminPassword, null, List.of(Profile.ADMIN)));
        }
    }

    private void saveCardInit(CardDTO cardDTO) {
        cardController.save(cardDTO);
    }

    private void saveUserInit(UserDTO userDTO) {
        userController.saveAdmin(userDTO);
    }
}
