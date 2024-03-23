import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BankProgram{
		private HashMap<Integer,Integer> accounts = new HashMap<>();
		private double rate = 0.01;
		private int nextacct = 0;
		private int current = -1;
		private Scanner scanner;
		private boolean done = false;

		public static void main(String[] args) {
			BankProgram program = new BankProgram();
			program.run();
		}
			
		public void run() {
			scanner = new Scanner(System.in);
			while (!done) {
				System.out.print("Enter command (0=quit, 1=new, 2=select, 3=deposit, 4=loan, 5=show, 6=interest): ");
				int cmd = scanner.nextInt();
				processCommand(cmd);
			}
			scanner.close();
		}

		private void processCommand(int cmd) {
			if (cmd == 0) quit();
			else if (cmd == 1) newAccount();
			else if (cmd == 2) select();
			else if (cmd == 3) deposit();
			else if (cmd == 4) authorizeLoan();
			else if (cmd == 5) showAll();
			else if (cmd == 6) addInterest();
			else
				System.out.println("illegal command");
		}

		public void quit(){
			done = true;
			System.out.println("Goodbye...");
		}

		private void newAccount(){
			current = nextacct++;
			accounts.put(current, 0);
			System.out.println("Account " + current + " created");
		}

		private void select(){
			System.out.println("Enter account number: ");
			current = scanner.nextInt();
			int balance = accounts.get(current);
			System.out.println("Account " + current + "Balance: " + balance);
		}

		private void deposit() {
			System.out.print("Enter deposit amount: ");
			int amt = scanner.nextInt();
			int balance = accounts.get(current);
			accounts.put(current, balance+amt);
		}

		private void authorizeLoan() {
			System.out.println("Enter loan amount: ");
			int loanamount = scanner.nextInt();
			int balance = accounts.get(current);
			if(balance >= loanamount / 2)
				// accounts.put(current, balance - loanamount);
				System.out.println("Loan authorized");
			else
				System.out.println("Loan denied");
		}

		private void addInterest() {
			Set<Integer> accts = accounts.keySet();
			for (int i : accts) {
				int balance = accounts.get(i);
				int newbalance = (int) (balance * (1 + rate));
				accounts.put(i, newbalance);
			}
		}

		private void showAll() {
			Set<Integer> accts = accounts.keySet();
			for (int i : accts) {
				int balance = accounts.get(i);
				System.out.println("Account " + i + "Balance: " + balance);
			}
		}
}