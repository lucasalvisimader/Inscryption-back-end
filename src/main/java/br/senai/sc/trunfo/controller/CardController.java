package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.CardUpdateDTO;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import br.senai.sc.trunfo.model.enums.ImageType;
import org.springframework.http.ResponseEntity;
import br.senai.sc.trunfo.service.CardService;
import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.entity.Card;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Card> save(@RequestBody @Valid CardDTO objectDTO) {
        return ResponseEntity.ok(cardService.save(objectDTO));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/listAll")
    public ResponseEntity<Page<Card>> listAll(@RequestParam int page) {
        return ResponseEntity.ok(cardService.listAll(page, 12));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list/{id}")
    public ResponseEntity<Card> list(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.list(id));
    }

    @PreAuthorize("hasAuthority('PLAYER')")
    @GetMapping("/listFromUser/{name}/{password}")
    public ResponseEntity<List<Card>> listFromUser(@PathVariable String name, @PathVariable String password) {
        List<Card> cards = userService.listLogin(name, password).getCards();
        return ResponseEntity.ok(cards);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody @Valid CardUpdateDTO objectDTO) {
        return ResponseEntity.ok(cardService.update(id, objectDTO));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getImageTypes")
    public ResponseEntity<ImageType[]> getImages() {
        return ResponseEntity.ok(cardService.getImages());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getSigilsTypes")
    public ResponseEntity<SigilsType[]> getSigils() {
        return ResponseEntity.ok(cardService.getSigils());
    }
}
