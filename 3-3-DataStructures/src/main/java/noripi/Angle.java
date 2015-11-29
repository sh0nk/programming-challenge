package noripi;

public class Angle {
  private static final int PRECISION = 10;
  private double cosine;
  private double sine;

  private Angle(double cosine, double sine) {
    this.cosine = Math.round(cosine * Math.pow(10, PRECISION)) / Math.pow(10, PRECISION);
    this.sine = Math.round(sine * Math.pow(10, PRECISION)) / Math.pow(10, PRECISION);
  }

  public static Angle byRadians(double rad) {
    return new Angle(Math.cos(rad), Math.sin(rad));
  }

  public Angle getRotatedAngle(double cosDiff, double sinDiff) {
    // calc cos(A+B)
    double newCosine = this.cosine * cosDiff - this.sine * sinDiff;

    // calc sin(A+B)
    double newSine = this.sine * cosDiff + this.cosine * sinDiff;

    return new Angle(newCosine, newSine);
  }

  public Angle getRotatedAngle(Angle angle) {
    return this.getRotatedAngle(angle.getCosine(), angle.getSine());
  }

  public Angle getSubtractedAngle(double cosDiff, double sinDiff) {
    return this.getRotatedAngle(cosDiff, -sinDiff);
  }

  public Angle getSubtractedAngle(Angle angle) {
    // cos(A-B) & sin(A-B)
    return this.getRotatedAngle(angle.getCosine(), -angle.getSine());
  }

  public boolean isZero() {
    return this.sine == 0;
  }

  /**
   * @return the cosine
   */
  public double getCosine() {
    return this.cosine;
  }

  /**
   * @return the sine
   */
  public double getSine() {
    return this.sine;
  }

  @Override
  public String toString() {
    return "[" + this.cosine + "/" + this.sine + "]";
  }
}
