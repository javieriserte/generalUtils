package math.confusionmatrix;

public class ConfusionMatrix {
  //////////////////////////////////////////////////////////////////////////////
  // Instance variables
  private int truePositives;
  private int falsePositives;
  private int trueNegatives;
  private int falseNegatives;
  //////////////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////////////
  // Constructor
  public ConfusionMatrix(int truePositives, int trueNegatives,
      int falsePositives, int falseNegatives) {
    this.setTruePositives(truePositives);
    this.setTrueNegatives(trueNegatives);
    this.setFalsePositives(falsePositives);
    this.setFalseNegatives(falseNegatives);
  }
  public ConfusionMatrix () {
    this.setTruePositives(0);
    this.setTrueNegatives(0);
    this.setFalsePositives(0);
    this.setFalseNegatives(0);
  }
  //////////////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////////////
  // Public interface
  public double accuracy() {
    return ((double)(this.getTruePositives() + this.getTrueNegatives())) / (double) this.getTotal();
  }
  public int getActualPositives() {
    return this.getTruePositives() + this.getFalseNegatives();
  }
  public int getPredictedPositives() {
    return this.getTruePositives() + this.getFalsePositives();
  }
  public int getActualNegatives() {
    return this.getTrueNegatives() + this.getFalsePositives();
  }
  public int getPredictedNegatives() {
    return this.getTrueNegatives() + this.getFalseNegatives();
  }
  public int getTotal() {
    return this.getPredictedNegatives() + this.getPredictedPositives();
  }
  /**
   * @return the truePositives
   */
  public int getTruePositives() {
    return truePositives;
  }
  /**
   * @param truePositives the truePositives to set
   */
  public void setTruePositives(int truePositives) {
    this.truePositives = truePositives;
  }
  /**
   * @return the falsePositives
   */
  public int getFalsePositives() {
    return falsePositives;
  }
  /**
   * @param falsePositives the falsePositives to set
   */
  public void setFalsePositives(int falsePositives) {
    this.falsePositives = falsePositives;
  }
  /**
   * @return the trueNegatives
   */
  public int getTrueNegatives() {
    return trueNegatives;
  }
  /**
   * @param trueNegatives the trueNegatives to set
   */
  public void setTrueNegatives(int trueNegatives) {
    this.trueNegatives = trueNegatives;
  }
  /**
   * @return the falseNegatives
   */
  public int getFalseNegatives() {
    return falseNegatives;
  }
  /**
   * @param falseNegatives the falseNegatives to set
   */
  public void setFalseNegatives(int falseNegatives) {
    this.falseNegatives = falseNegatives;
  }
  //////////////////////////////////////////////////////////////////////////////
}
