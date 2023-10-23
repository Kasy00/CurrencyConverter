package collections;

import currencies.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyCollection implements ICurrencyCollection<Currency> {
    private static CurrencyCollection instance;
    private List<Currency> currencies = new ArrayList<>();

    private CurrencyCollection(){

    }

    public static CurrencyCollection getInstance(){
        if(instance == null){
            instance = new CurrencyCollection();
        }
        return instance;
    }
    @Override
    public void add(Currency currency){
        currencies.add(currency);
    }

    @Override
    public Currency get(int index){
        return currencies.get(index);
    }

    @Override
    public int size(){
        return currencies.size();
    }
}
