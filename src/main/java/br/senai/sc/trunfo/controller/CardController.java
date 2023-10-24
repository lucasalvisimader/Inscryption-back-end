package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.dto.CardUpdateDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.service.CardService;
import br.senai.sc.trunfo.service.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"
)
@NoArgsConstructor
@RequestMapping("/card")
public class CardController {
    private CardService cardService;
    private UserService userService;

    @Autowired
    private void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<Card> save(@RequestBody @Valid CardDTO objectDTO) {
        return ResponseEntity.ok(cardService.save(objectDTO));
    }

    @GetMapping("/listAll")
    public ResponseEntity<Page<Card>> listAll(@RequestParam int page) {
        return ResponseEntity.ok(cardService.listAll(page, 12));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Card> list(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.list(id));
    }

    @GetMapping("/listFromUser/{name}/{password}")
    public ResponseEntity<List<Card>> listFromUser(@PathVariable String name, @PathVariable String password) {
        List<Card> cards = userService.listLogin(name, password).getCards();
        return ResponseEntity.ok(cards);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody @Valid CardUpdateDTO objectDTO) {
        return ResponseEntity.ok(cardService.update(id, objectDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getImageTypes")
    public ResponseEntity<ImageType[]> getImages() {
        return ResponseEntity.ok(cardService.getImages());
    }

    @GetMapping("/getSigilsTypes")
    public ResponseEntity<SigilsType[]> getSigils() {
        return ResponseEntity.ok(cardService.getSigils());
    }
}
