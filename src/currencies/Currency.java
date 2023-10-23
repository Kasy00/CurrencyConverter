package currencies;

public class Currency implements ICurrency {
    private String code;
    private String name;
    private double exchangeRate;

    private double factor;

    public Currency(String code, String name, double exchangeRate, double factor){
        this.code = code;
        this.name = name;
        this.exchangeRate = exchangeRate;
        this.factor = factor;
    }

    @Override
    public String getCode(){
        return code;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public double getExchangeRate(){
        return exchangeRate;
    }

    @Override
    public double getFactor(){
        return factor;
    }
}
