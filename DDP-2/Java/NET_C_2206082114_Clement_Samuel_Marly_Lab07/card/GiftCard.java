package card;

import item.Item;

public class GiftCard extends Card {
    // inisiasi object sesuai visibility dan variabel yang diperlukan
    public GiftCard(String companyName, double balance, String type) {
        super(companyName, balance, type);
    }
    //override method bayar
    @Override
    public void pay(Item item){
        setBalance(getBalance()-(item.getPrice()*0.9));
    }
}
