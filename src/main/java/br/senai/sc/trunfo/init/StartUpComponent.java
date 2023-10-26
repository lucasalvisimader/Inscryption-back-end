package br.senai.sc.trunfo.init;

import br.senai.sc.trunfo.controller.UserController;
import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.PriceType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.repository.CardRepository;
import br.senai.sc.trunfo.repository.UserRepository;
import br.senai.sc.trunfo.security.enums.Profile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartUpComponent implements CommandLineRunner {
    private CardRepository cardRepository;
    private UserRepository userRepository;
    private UserController userController;
    @Value("${global.admin.username}")
    private String adminUsername;
    @Value("${global.admin.password}")
    private String adminPassword;

    @Autowired
    private void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Autowired
    private void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            saveCardInit(new CardDTO("STOAT", 1, 3, List.of(SigilsType.NONE), ImageType.STOAT, PriceType.BLOOD1));
            saveCardInit(new CardDTO("STINKBUG", 1, 2, List.of(SigilsType.STINKY), ImageType.STINKBUG, PriceType.BONE2));
            saveCardInit(new CardDTO("STUNTED WOLF", 2, 2, List.of(SigilsType.NONE), ImageType.STUNTEDWOLF, PriceType.BLOOD1));
            saveCardInit(new CardDTO("WOLF", 3, 2, List.of(SigilsType.NONE), ImageType.WOLF, PriceType.BLOOD2));
            saveCardInit(new CardDTO("WOLF", 3, 2, List.of(SigilsType.NONE), ImageType.WOLF, PriceType.BLOOD2));
            saveCardInit(new CardDTO("RIVER SNAPPER", 1, 6, List.of(SigilsType.NONE), ImageType.RIVERSNAPPER, PriceType.BLOOD2));
            saveCardInit(new CardDTO("SQUIRREL", 0, 1, List.of(SigilsType.NONE), ImageType.SQUIRREL, PriceType.NONE));
            saveCardInit(new CardDTO("WOLF CUB", 1, 1, List.of(SigilsType.FLEDGELING), ImageType.WOLFCUB, PriceType.BLOOD1));
            saveCardInit(new CardDTO("COYOTE", 2, 1, List.of(SigilsType.NONE), ImageType.COYOTE, PriceType.BONE4));
            saveCardInit(new CardDTO("BLOODHOUND", 2, 3, List.of(SigilsType.GUARDIAN), ImageType.BLOODHOUND, PriceType.BLOOD2));
            saveCardInit(new CardDTO("ALPHA", 1, 2, List.of(SigilsType.LEADER), ImageType.ALPHA, PriceType.BONE5));
            saveCardInit(new CardDTO("URAYULI", 7, 7, List.of(SigilsType.NONE), ImageType.URAYULI, PriceType.BLOOD4));
            saveCardInit(new CardDTO("GRIZZLY", 4, 6, List.of(SigilsType.NONE), ImageType.GRIZZLY, PriceType.BLOOD3));
            saveCardInit(new CardDTO("GREAT WHITE", 4, 2, List.of(SigilsType.WATERBORNE), ImageType.GREATWHITE, PriceType.BLOOD3));
            saveCardInit(new CardDTO("AMOEBA", 1, 2, List.of(SigilsType.AMORPHOUS), ImageType.AMOEBA, PriceType.BONE2));
            saveCardInit(new CardDTO("AMALGAM", 3, 3, List.of(SigilsType.NONE), ImageType.AMALGAM, PriceType.BLOOD2));
            saveCardInit(new CardDTO("MOLE MAN", 0, 6, List.of(SigilsType.MIGHTYLEAP, SigilsType.BURROWER), ImageType.MOLEMAN, PriceType.BLOOD1));
            saveCardInit(new CardDTO("TURKEY VULTURE", 3, 3, List.of(SigilsType.AIRBORNE), ImageType.TURKEYVULTURE, PriceType.BONE8));
            saveCardInit(new CardDTO("SPARROW", 1, 2, List.of(SigilsType.AIRBORNE), ImageType.SPARROW, PriceType.BLOOD1));
            saveCardInit(new CardDTO("RAVEN EGG", 0, 2, List.of(SigilsType.FLEDGELING), ImageType.RAVENEGG, PriceType.BLOOD1));
            saveCardInit(new CardDTO("RAVEN", 2, 3, List.of(SigilsType.AIRBORNE), ImageType.RAVEN, PriceType.BLOOD2));
            saveCardInit(new CardDTO("MAGPIE", 1, 1, List.of(SigilsType.AIRBORNE, SigilsType.HOARDER), ImageType.MAGPIE, PriceType.BLOOD2));
            saveCardInit(new CardDTO("KINGFISHER", 1, 1, List.of(SigilsType.WATERBORNE, SigilsType.AIRBORNE), ImageType.KINGFISHER, PriceType.BLOOD1));
            saveCardInit(new CardDTO("RAT KING", 2, 1, List.of(SigilsType.BONEKING), ImageType.RATKING, PriceType.BLOOD2));
            saveCardInit(new CardDTO("FIELD MICE", 2, 2, List.of(SigilsType.FECUNDITY), ImageType.FIELDMICE, PriceType.BLOOD2));
            saveCardInit(new CardDTO("BEAVER", 1, 3, List.of(SigilsType.DAMBUILDER), ImageType.BEAVER, PriceType.BLOOD2));
            saveCardInit(new CardDTO("RABBIT", 0, 1, List.of(SigilsType.NONE), ImageType.RABBIT, PriceType.NONE));
            saveCardInit(new CardDTO("PORCUPINE", 1, 2, List.of(SigilsType.SHARPQUILLS), ImageType.PORCUPINE, PriceType.BLOOD1));
            saveCardInit(new CardDTO("STRANGELARVA", 0, 3, List.of(SigilsType.FLEDGELING), ImageType.STRANGELARVA, PriceType.BLOOD1));
            saveCardInit(new CardDTO("STRANGEPUPA", 0, 3, List.of(SigilsType.FLEDGELING), ImageType.STRANGEPUPA, PriceType.BLOOD1));
            saveCardInit(new CardDTO("MOTHMAN", 7, 3, List.of(SigilsType.AIRBORNE), ImageType.MOTHMAN, PriceType.BLOOD1));
            saveCardInit(new CardDTO("BEE", 1, 1, List.of(SigilsType.AIRBORNE), ImageType.BEE, PriceType.NONE));
            saveCardInit(new CardDTO("RING WORM", 0, 1, List.of(SigilsType.NONE), ImageType.RINGWORM, PriceType.BLOOD1));
            saveCardInit(new CardDTO("ANT QUEEN", 1, 3, List.of(SigilsType.ANTSPAWNER), ImageType.ANTQUEEN, PriceType.BLOOD2));
            saveCardInit(new CardDTO("MANTISGOD", 1, 1, List.of(SigilsType.TRIFURCATEDSTRIKE), ImageType.MANTISGOD, PriceType.BLOOD1));
            saveCardInit(new CardDTO("MANTIS", 1, 1, List.of(SigilsType.BIFURCATEDSTRIKE), ImageType.MANTIS, PriceType.BLOOD1));
            saveCardInit(new CardDTO("WORKER ANT", 1, 2, List.of(SigilsType.NONE), ImageType.WORKERANT, PriceType.BLOOD1));
            saveCardInit(new CardDTO("CORPSE MAGGOTS", 1, 2, List.of(SigilsType.CORPSEEATER), ImageType.CORPSEMAGGOTS, PriceType.BONE5));
            saveCardInit(new CardDTO("COCKROACH", 1, 1, List.of(SigilsType.UNKILLABLE), ImageType.COCKROACH, PriceType.BONE4));
            saveCardInit(new CardDTO("BEEHIVE", 0, 2, List.of(SigilsType.BEESWITHIN), ImageType.BEEHIVE, PriceType.BLOOD1));
            saveCardInit(new CardDTO("GECK", 1, 1, List.of(SigilsType.NONE), ImageType.GECK, PriceType.NONE));
            saveCardInit(new CardDTO("SKINK", 1, 2, List.of(SigilsType.LOOSETAIL), ImageType.SKINK, PriceType.BLOOD1));
            saveCardInit(new CardDTO("RATTLER", 3, 1, List.of(SigilsType.NONE), ImageType.RATTLER, PriceType.BONE6));
            saveCardInit(new CardDTO("ADDER", 1, 1, List.of(SigilsType.TOUCHOFDEATH), ImageType.ADDER, PriceType.BLOOD2));
            saveCardInit(new CardDTO("BULLFROG", 1, 2, List.of(SigilsType.MIGHTYLEAP), ImageType.BULLFROG, PriceType.BLOOD1));
            saveCardInit(new CardDTO("OUROBOROS", 1, 1, List.of(SigilsType.NONE), ImageType.OUROBOROS, PriceType.BLOOD2));
            saveCardInit(new CardDTO("PRONGHORN", 1, 3, List.of(SigilsType.SPRINTER, SigilsType.BIFURCATEDSTRIKE), ImageType.PRONGHORN, PriceType.BLOOD2));
            saveCardInit(new CardDTO("MOOSE BUCK", 3, 7, List.of(SigilsType.HEFTY), ImageType.MOOSEBUCK, PriceType.BLOOD3));
            saveCardInit(new CardDTO("ELK FAWN", 1, 1, List.of(SigilsType.SPRINTER, SigilsType.FLEDGELING), ImageType.ELKFAWN, PriceType.BLOOD1));
            saveCardInit(new CardDTO("ELK", 2, 4, List.of(SigilsType.SPRINTER), ImageType.ELK, PriceType.BLOOD2));
            saveCardInit(new CardDTO("BLACK GOAT", 0, 1, List.of(SigilsType.WORTHYSACRIFICE), ImageType.BLACKGOAT, PriceType.BLOOD1));
            saveCardInit(new CardDTO("CHILD 13", 0, 1, List.of(SigilsType.MANYLIVES), ImageType.CHILD13, PriceType.BLOOD1));
            saveCardInit(new CardDTO("THE SMOKE", 0, 1, List.of(SigilsType.BONEKING), ImageType.THESMOKE, PriceType.NONE));
            saveCardInit(new CardDTO("GREATER SMOKE", 1, 3, List.of(SigilsType.BONEKING), ImageType.GREATERSMOKE, PriceType.NONE));
            saveCardInit(new CardDTO("OPOSSUM", 1, 1, List.of(SigilsType.NONE), ImageType.OPOSSUM, PriceType.BONE2));
            saveCardInit(new CardDTO("RIVER OTTER", 1, 1, List.of(SigilsType.WATERBORNE), ImageType.RIVEROTTER, PriceType.BLOOD1));
            saveCardInit(new CardDTO("BAT", 2, 1, List.of(SigilsType.AIRBORNE), ImageType.BAT, PriceType.BONE4));
            saveCardInit(new CardDTO("SKUNK", 0, 3, List.of(SigilsType.STINKY), ImageType.SKUNK, PriceType.BLOOD1));
            saveCardInit(new CardDTO("CAT", 0, 1, List.of(SigilsType.MANYLIVES), ImageType.CAT, PriceType.BLOOD1));
            saveCardInit(new CardDTO("UNDEAD CAT", 3, 6, List.of(SigilsType.NONE), ImageType.UNDEADCAT, PriceType.BLOOD1));
            saveCardInit(new CardDTO("MOLE", 0, 4, List.of(SigilsType.BURROWER), ImageType.MOLE, PriceType.BLOOD1));
            saveCardInit(new CardDTO("DAM", 0, 2, List.of(SigilsType.NONE), ImageType.DAM, PriceType.NONE));
            saveCardInit(new CardDTO("BOULDER", 0, 5, List.of(SigilsType.NONE), ImageType.BOULDER, PriceType.NONE));
            saveCardInit(new CardDTO("STUMP", 0, 3, List.of(SigilsType.NONE), ImageType.STUMP, PriceType.NONE));
            saveCardInit(new CardDTO("GRANDFIR", 0, 4, List.of(SigilsType.MIGHTYLEAP), ImageType.GRANDFIR, PriceType.NONE));
            saveCardInit(new CardDTO("SNOWYFIR", 0, 4, List.of(SigilsType.MIGHTYLEAP), ImageType.SNOWYFIR, PriceType.NONE));
            saveUserInit(new UserDTO(adminUsername, adminPassword));
        }
    }

    private void saveCardInit(CardDTO objectDTO) {
        Card card = new Card();
        BeanUtils.copyProperties(objectDTO, card);
        cardRepository.save(card);
    }

    private void saveUserInit(UserDTO objectDTO) {
        User user = new User();
        BeanUtils.copyProperties(objectDTO, user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthorities(List.of(Profile.ADMIN));
        userRepository.save(user);
    }
}
