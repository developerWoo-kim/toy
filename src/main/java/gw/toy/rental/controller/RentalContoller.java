package gw.toy.rental.controller;

import gw.toy.member.domain.Member;
import gw.toy.member.service.MemberService;
import gw.toy.rental.domain.RentalBasket;
import gw.toy.rental.domain.RentalItem;
import gw.toy.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RentalContoller {

    private final MemberService memberService;
    private final RentalService rentalService;

    @GetMapping("/rentalItem/list")
    public String rentalItemList(Model model) {

        List<RentalItem> rentalItemList = rentalService.findAll();
        model.addAttribute("rentalItemList", rentalItemList);

        return "/rental/item/rentalItemList";
    }

    @GetMapping("/rentalItem/new")
    public String rentalItemForm() {
        return "/rental/item/rentalItemForm";
    }

    @PostMapping("/rentalItem/save")
    public String save(RentalItem rentalItem) {
        rentalService.save(rentalItem);
        return "redirect:/";
    }

    @GetMapping("/list/{memberId}/basket")
    public String basketList(@PathVariable Long memberId, Model model) {
        List<RentalBasket> basketList = rentalService.findBasketList(memberId);

        model.addAttribute("basketList", basketList);

        return "rental/basket/basketList";
    }

    @ResponseBody
    @PostMapping("/add/{rentalItemId}/basket")
    public String addItemToBasket(@PathVariable("rentalItemId") Long rentalItemId) {
        String result = rentalService.addItemToBasket(rentalItemId);
        return result;
    }
}
