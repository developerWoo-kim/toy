package gw.toy.rental.service;

import com.google.gson.JsonObject;
import gw.toy.member.domain.Member;
import gw.toy.rental.domain.RentalBasket;
import gw.toy.rental.domain.RentalItem;
import gw.toy.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    @Transactional
    public RentalItem findOne(Long itemId) {
        RentalItem findOne = rentalRepository.findOne(itemId);
        return findOne;
    }

    @Transactional
    public List<RentalItem> findAll() {
        List<RentalItem> findAll = rentalRepository.findAll();
        return findAll;
    }

    @Transactional
    public void save(RentalItem rentalItem) {
        rentalRepository.save(rentalItem);
    }

    @Transactional
    public List<RentalBasket> findBasketList(Long id) {
        return rentalRepository.findBasketList(id);
    }
    @Transactional
    public String addItemToBasket(Long id) {
        JsonObject json = new JsonObject();
        RentalBasket itemInBasket = rentalRepository.findItemInBasket(id);

        if(itemInBasket != null) {
            Member member = new Member();
            member.setId(1L);
            RentalItem rentalItem = new RentalItem();
            rentalItem.setRentalItemId(id);

            RentalBasket rentalBasket = new RentalBasket(member, rentalItem, 2);
            rentalRepository.addItemToBasket(rentalBasket);

            json.addProperty("status", "success");
            json.addProperty("message", "장바구니에 담겼습니다.");
        } else {
            json.addProperty("status", "fail");
            json.addProperty("message", "이미 장바구니에 존재합니다.");
        }
        return json.toString();
    }
}
