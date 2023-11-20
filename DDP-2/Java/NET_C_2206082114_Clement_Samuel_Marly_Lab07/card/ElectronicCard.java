package card;

import item.Item;

public class ElectronicCard extends Card implements Topupable {
    // inisiasi object sesuai visibility dan variabel yang diperlukan
    public ElectronicCard(String companyName, double balance, String type) {
        super(companyName, balance, type);
    }
    //override method bayar
    @Override
    public void pay(Item item){
        setBalance(getBalance()-item.getPrice());
    }
    public void topup (double amount){
        setBalance((getBalance() + amount));
    }
}
