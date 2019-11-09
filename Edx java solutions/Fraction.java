public class Fraction {
    private int numerator;
    private int denominator;

    //Constructors:
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator can't be zero!");
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
            if (this.denominator < 0) {
                this.denominator *= -1;
                this.numerator *= -1;
            }
        }
    }

    public Fraction(int numerator) { // initializes the Fraction object equal in value to the integer parameter
        this(numerator, 1);
    }

    public Fraction() { // initializes a zero parameter Fraction object with numerator = 0
        this(0);
    }

    //Methods:
    public int getNumerator() {
        int result;
        result = this.numerator;
        return result;
    }

    public int getDenominator() {
        int result;
        result = this.denominator;
        return result;
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    public Fraction add(Fraction otherFraction) {
        Fraction result = new Fraction();
        result.denominator = this.denominator * otherFraction.denominator;
        this.numerator = result.denominator / this.denominator * this.numerator;
        otherFraction.numerator = result.denominator / otherFraction.denominator * otherFraction.numerator;
        result.numerator = this.numerator + otherFraction.numerator;
        result.toLowestTerms();
        result.zeroResult();
        return result;
    }

    public Fraction subtract(Fraction otherFraction) {
        Fraction result = new Fraction();
        result.denominator = this.denominator * otherFraction.denominator;
        this.numerator = result.denominator / this.denominator * this.numerator;
        otherFraction.numerator = result.denominator / otherFraction.denominator * otherFraction.numerator;
        result.numerator = this.numerator - otherFraction.numerator;
        result.toLowestTerms();
        result.zeroResult();
        return result;
    }

    public Fraction multiply(Fraction otherFraction) {
        Fraction result = new Fraction();
        if (this.numerator != 0) {
            result.numerator = this.numerator * otherFraction.numerator;
            result.denominator = this.denominator * otherFraction.denominator;
            result.toLowestTerms();
            result.zeroResult();
            return result;
        } else {
            result.numerator =0;
            result.denominator = 1;
            return result;
        }
    }

    public Fraction divide(Fraction otherFraction) {
        Fraction result = new Fraction();
        if (this.numerator != 0) {
            result.numerator = this.numerator * otherFraction.denominator;
            result.denominator = this.denominator * otherFraction.numerator;
            result.toLowestTerms();
            result.zeroResult();
            return result;
        } else {
            result.numerator = 0;
            result.denominator = 1;
            return result;
        }
    }

    public boolean equals(Object otherFraction) {
        if (otherFraction instanceof Fraction) {
            ((Fraction) otherFraction).toLowestTerms();
            this.toLowestTerms();
            if (this.numerator == ((Fraction) otherFraction).numerator && this.denominator == ((Fraction) otherFraction).denominator) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("You are not comparing two fractions");
            return false;
        }
    }

    private void toLowestTerms() { // converts the current fraction to the lowest terms
        int gdc = gdc(this.numerator, this.denominator);
        if (gdc != 0) {
            this.numerator /= gdc;
            this.denominator /= gdc;
            if (this.denominator < 0) {
                this.denominator *= -1;
                this.numerator *= -1;
            }
        }
    }

    private static int gdc(int numerator, int denominator) {
        while (numerator != 0 && denominator != 0) {
            int reminder = numerator % denominator;
            numerator = denominator;
            denominator = reminder;
        }
        return numerator;
    }

    private void zeroResult() {
        if (this.numerator == 0) {
            this.denominator = 1;
        }
    }
}
