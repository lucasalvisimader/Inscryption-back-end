package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.dto.CardUpdateDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.service.CardService;
import br.senai.sc.trunfo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
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

    @GetMapping("/list/{id}")
    public ResponseEntity<Card> list(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.list(id));
    }

    @GetMapping("/listFromUser")
    public ResponseEntity<List<List<Card>>> listFromUser(@NotNull HttpServletRequest request) {
        return ResponseEntity.ok(cardService.listFromUser(request));
    }

    @GetMapping("/listAll")
    public ResponseEntity<Page<Card>> listAll(@RequestParam int page) {
        return ResponseEntity.ok(cardService.listAll(page, 12));
    }

    @GetMapping("/qtyCost/{id}")
    public ResponseEntity<Integer> qtyCost(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.qtyCost(id));
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
