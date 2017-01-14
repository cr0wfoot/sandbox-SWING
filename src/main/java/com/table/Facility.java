package com.table;

public class Facility {

    private static final int BILL_FORMAT_WORD_MAX_LENGHT = 13;
    
    private Integer id;
    private String name;
    private Integer count;
    private Integer price;
    private Double categoryCoeff;

    /**
     * Get the value of categoryCoeff
     *
     * @return the value of categoryCoeff
     */
    public Double getCategoryCoeff() {
        return categoryCoeff;
    }

    /**
     * Set the value of categoryCoeff
     *
     * @param categoryCoeff new value of categoryCoeff
     */
    public void setCategoryCoeff(Double categoryCoeff) {
        this.categoryCoeff = categoryCoeff;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Facility() {
    }

    public Facility(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = 0;
        this.categoryCoeff = 1.;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private String convertNameToBillFormat(String name) {
        String[] split = name.split(" ");
        String convertedName;
        if (split.length > 1) {
            convertedName = getLimitedSubString(split[0])
                            .concat("\n")
                            .concat(getLimitedSubString(split[1]))
                            .toUpperCase();
        } else {
            convertedName = getLimitedSubString(split[0])
                            .concat("\n ")
                            .toUpperCase();
        }
        return convertedName;
    }

    private String getLimitedSubString(String input) {
        String subStr = input.codePointCount(0, input.length()) > BILL_FORMAT_WORD_MAX_LENGHT
                ? input.substring(0, input.offsetByCodePoints(0, BILL_FORMAT_WORD_MAX_LENGHT)) : input;
        return subStr;
    }

    @Override
    public Facility clone() {
        Facility data = new Facility();
        data.setId(id);
        data.setName(convertNameToBillFormat(name));
        data.setCount(count);
        data.setPrice(price);
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Facility other = (Facility) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Data{" + "id=" + id + ", name=" + name + ", count=" + count + ", price=" + price + '}';
    }
}
