package noripi;

public class PolarPoint {
  private static final int PRECISION = 4;
  private double length;
  private Angle angle;

  public PolarPoint(double length, Angle angle) {
    this.length = length;
    this.angle = angle;
  }

  @Override
  public String toString() {
    return "[Len:" + Math.round(this.length * Math.pow(10, PRECISION)) / Math.pow(10, PRECISION)
        + " Angle:" + this.getAngle() + "] ";
  }

  public double[] toRectangular() {
    return new double[] {
        Math.round(this.length * this.getAngle().getCosine() * Math.pow(10, PRECISION))
            / Math.pow(10, PRECISION),
        Math.round(this.length * this.getAngle().getSine() * Math.pow(10, PRECISION))
            / Math.pow(10, PRECISION)};
  }

  /**
   * @return the length
   */
  public double getLength() {
    return this.length;
  }

  /**
   * @return the angle
   */
  public Angle getAngle() {
    return this.angle;
  }

  /**
   * @param angle the angle to set
   */
  public void setAngle(Angle angle) {
    this.angle = angle;
  }
}
