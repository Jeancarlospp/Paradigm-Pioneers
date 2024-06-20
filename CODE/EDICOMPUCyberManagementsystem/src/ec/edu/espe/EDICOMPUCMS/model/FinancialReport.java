
package ec.edu.espe.EDICOMPUCMS.model;

public class FinancialReport {
    private int reportID;
    private double totalIncome;
    private double totalExpenses;
    private double profit;

    public FinancialReport(int reportID, double totalIncome, double totalExpenses, double profit) {
        this.reportID = reportID;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.profit = profit;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "FinancialReport{" + "reportID=" + reportID + ", totalIncome=" + totalIncome + ", totalExpenses=" + totalExpenses + ", profit=" + profit + '}';
    }
}
