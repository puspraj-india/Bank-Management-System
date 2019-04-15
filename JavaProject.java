import java.util.Scanner;
import java.util.Random;
import java.lang.String;


//***************************************CLASS USERACCOUNT********************************************

abstract class UserAccount {
    private String userName;
    private double currentAmount;
    private int age;
    public int id;
	
    /**************************************************************/
	/**************************ADD USER****************************/
	
    public int add_user()
    {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter User Name\t");
        try
        {
            boolean flag=false;
            userName = scan.nextLine();
           System.out.println();
            userName+='\0';
            char arr[]=userName.toCharArray();
            for(int i=0;arr[i]!='\0';i++ )
                if((arr[i]>='A'&&arr[i]<='Z')||(arr[i]>='a'&&arr[i]<='z')||(arr[i]==' '))
                {
                    flag = true;
                }
                else {
					flag = false;
				break;}
            if(flag==false)
            {
                Exception e = new Exception("User Name is Invalid");
                throw e;
            }
            else 
            {
                userName = userName.substring(0,userName.length()-1);
                
            }
        }
        catch(Exception e)
        {
            System.out.println("User name is invalid");  
				System.exit(0);
        }
        System.out.print("Enter age in years\t");
        try
        {
            age = scan.nextInt();
            System.out.println();
            if(age >120||age<0)
            {
                Exception e1= new Exception();
                throw e1;
            }
        }        
        catch(Exception e1)
        {
            System.out.println("The age is invalid");
			System.exit(0);
        }
        System.out.print("Enter the amount\t");
        try
        {
            currentAmount = scan.nextDouble();
            System.out.println();
            if(currentAmount<=0)
            {
                Exception e2 = new Exception();
                throw e2;
            }
        }
         catch(Exception e4)
            {
                System.out.println("Invalid Amount Exception");
				System.exit(0);
            }
        int x=0;
        id=0;
        for(int i=0;i<6;i++)
        {
            id=id*10;
           x= rand.nextInt(10);
           while(x==0)
           {
               x= rand.nextInt(10);
           }
           id+=x;
        }
        System.out.println("The id of the user is \t"+id);       
        return 0;
        }
	/*********************************************************************************/
    /*************************DISPLAY USER RECORD*************************************/
    public void display_user_record()
    {
        
       System.out.println("The id of the user is \t"+id);
       System.out.println("Name of the user is \t"+userName);
       System.out.println("The age of the user is \t"+age);
       System.out.println("The Amount of the user \t"+currentAmount);
       
    }
    /*********************************************************************************/
	/**********************************DELETE USER************************************/
    public int delete_user(UserAccount arr[],int size)
    {
        int i,j;
        Scanner scan = new Scanner(System.in);
        int id=scan.nextInt();
        for(i =0;i<size;i++)
        {
            if(id==arr[i].id)
            {
                break;
            }
        }
        if(i==size)
        {
            System.out.println("The id is not present");
            return size;
        }
        
        for(j=0;j<size;j++)
        {
            if(j==i)
            {
                arr[j]=arr[j+1];
            }
        }
        System.out.println("The data deleted successfully");
        return size-1;
    }
    /******************************************************************************/
	/********************************MODIFY****************************************/
    public void modify()
    {
        int i,choice;
        Scanner scan = new Scanner(System.in);
       
                System.out.println("Press 1 to update User Name ");
                System.out.println("Press 2 to update amount ");
                System.out.println("Press 3 to update age");
                System.out.print("Enter your choice\t");
                choice = scan.nextInt();
                switch(choice)
                {
                    case 1:
                        System.out.print("Enter the name of the user\t");
        try
        {
            boolean flag=true;
            String s= userName;
            scan.nextLine();
            userName = scan.nextLine();
            userName+='\0';
            char arr1[]=userName.toCharArray();
            for(int l=0;arr1[l]!='\0';l++ )
                if(!((arr1[l]>='A'&&arr1[l]<='Z')||(arr1[l]>='a'&&arr1[l]<='z')||(arr1[l]==' ')))
                {
                    flag = false;
                    break;
                }                      
            if(flag==false)
            {
                userName=s;
                Exception e = new Exception("User Name is Invalid");
                throw e;
            }
            else 
            {
               userName=userName.substring(0,arr1.length-1);
                System.out.println(" Name Updated Successfully");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }                       
                        break;
                    case 2:
                        System.out.print("Enter the amount\t");
        try
        {
           currentAmount = scan.nextDouble();
            double d=currentAmount;
            if(currentAmount<=0)
            {
                currentAmount=d;
                Exception e2 = new Exception();
                throw e2;
            }
            else System.out.println("Amount updated Successfully");
        }
         catch(Exception e4)
            {
                System.out.println("Invalid Amount Exception");
            }
        break;
                    case 3:
                        System.out.print("Enter age in years\t");
        try
        {
            age = scan.nextInt();
            int a = age;
            if(age >120||age<=0)
            {
                age=a;
                Exception e1= new Exception();
                throw e1;
            }
            else {
                System.out.println("Age updated Successfully");
            }
        }
        catch(Exception e1)
        {
            System.out.println("The age is over limit");
        }
        break;
                    default:
                        System.out.println("The choice is invalid");                                 
                }
    }
    public double updateCurrentAmount(double amount)
    {
        currentAmount+=amount;
        return currentAmount;
    }
    public double getCurrentAmount()
    {
        return currentAmount;
    }
    abstract public  void display_balance(int id);
    }
	
	/*************************************************************************************************/
	/***************************************CLASS CREDIT*********************************************/
	
	class credit extends UserAccount{
   public double amount;
   
   @Override
    public void display_balance(int id)
    {
        System.out.println("The Current balance of user is "+super.getCurrentAmount());
    }
    public void credit_amount(double amount)
    {
       try
       {
           if(amount>0)
        {
            double msg=this.updateCurrentAmount(amount);
            System.out.println(amount+" Credited to your account");
            System.out.println("The updated balance is "+msg);
        }
           else
               System.out.println("Amount cannot be added");
        
    }
       catch(Exception e)
       {
           System.out.println("Amount is not valid");
       }
    }
}

	
	
	
	
//***************************************CLASS DEBIT********************************************
	
	

	class debit extends UserAccount {
    public double amount;
    @Override
    public void display_balance(int id)
    {
        System.out.println("The Current balance of user is "+this.getCurrentAmount());
    }
    public void debit_amount(double amount)
    {
        if(super.getCurrentAmount()<amount||amount ==0)
        {
            System.out.println("The amount cannot be debited");
        }
        else
        {
            this.updateCurrentAmount(-amount);
            System.out.println(amount+" debited from your account");
            System.out.println("The updated balance is "+this.getCurrentAmount());
        }
    }
    
    
}	
	/*****************************************************************************************************/
	/***************************************CLASS BANKACCOUNT********************************************/
	
 class BankAccount {

    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int choice,t,checkid,i,mainChoice;
		int size=0;
        char ch='n';
        double amount;
        credit[] arr1 = new credit[30]; 
        debit[] arr2= new debit[30];
        UserAccount cobj = new credit(); 
        UserAccount dobj = new debit();
         String s;
         
         try
             
         {System.out.println("Press 1 for credit account");
         System.out.println("Press 2 for debit account");
         mainChoice = scan.nextInt();
           if(mainChoice ==1)
           {
      try
        {
        do
        {
        System.out.println("Press 1 to add an Account");
        System.out.println("Press 2 to delete the Account");
        System.out.println("Press 3 to Modify the Account");
        System.out.println("Press 4 to display the Account Details");
        System.out.println("Press 5 for display balance of Account");
        System.out.println("Press 6 to credit to a account");
        System.out.println("Press 7 for exit\t");
		System.out.print("Enter your choice\t");
        try
        {
            
          choice  = scan.nextInt();
        switch(choice)
        {
            case 1:
                try
                { 
                    arr1[size]= new credit();
                t = arr1[size].add_user();
                if(t==0)
                size++;
                
                }
               catch(Exception e)
                {
                    System.out.println("Error....!!!");
                }scan.nextLine();
                break;
            case 2:
                System.out.print("Enter the user id \t");
                size=cobj.delete_user(arr1,size);
                scan.nextLine();
                break;
            case 3:
                
                System.out.print("Enter id for modification\t");
                checkid = scan.nextInt();                
                for(i =0;i<size;i++)
                {
                    if(checkid==arr1[i].id)
                    
                    {
                        arr1[i].modify();
                        break;
                    }
                }
                if(i==size)
                    System.out.println("Id not found");  
                scan.nextLine();
                break;
            case 4:
               System.out.print("Enter the id for display\t");
                checkid = scan.nextInt();
                for(i =0;i<size;i++)
                {
                    if(checkid==arr1[i].id)
                    {arr1[i].display_user_record();   
                    break;}
                }
                if(i==size)
                    System.out.println("Id not found");  
                scan.nextLine();
                break;
            case 5:
                System.out.print("Enter the id\t");
                checkid= scan.nextInt();
                for(i=0;i<size;i++)
                {
                    if(checkid==arr1[i].id)
                    {
                        arr1[i].display_balance(checkid);
                        break;
                    }
                }
                if(i==size)
                    System.out.println("Id not found");
                break;
            case 6:
                 System.out.print("Enter the id\t");
                checkid= scan.nextInt();
                for(i=0;i<size;i++)
                {
                    if(checkid==arr1[i].id)
                    {
                        System.out.print("Enter the amount to be credited\t");
                        amount = scan.nextDouble();
                        arr1[i].credit_amount(amount);
                        break;
                    }
                }
                if(i==size)
                    System.out.println("Id not found");
                break;
            case 7:
                System.out.println("Compile again");
                break;
            default:
                System.out.println("Entered Wrong choice"); 
                break;
        } 
        System.out.print("Press (Y/N) to continue\t");
        ch='n';
        ch = scan.next().charAt(0);
        }
       catch(Exception e)
       {
           System.out.println("Wrong input the program is terminating");
           System.exit(0);
       }}while(ch=='Y'||ch=='y');
        }
        catch(Exception e)
        {
            System.out.println("Sorry the program is terminated due to some wrong input");
        }
       
    }
           else if(mainChoice==2)
    {
        try
        {
        do
        {
        System.out.println("Press 1 to add an Account");
        System.out.println("Press 2 to delete the Account");
        System.out.println("Press 3 to Modify the Account");
        System.out.println("Press 4 to display the Account Details");
        System.out.println("Press 5 for display balance of Account");
        System.out.println("Press 6 to debit from Account");
        System.out.println("Press 7 for exit ");
		System.out.print("Enter your choice\t");
        try
        {
            
          choice  = scan.nextInt();
        switch(choice)
        {
            case 1:
                try
                { 
                    arr2[size]= new debit();
                t = arr2[size].add_user();
                if(t==0)
                size++;
                
                }
               catch(Exception e)
                {
                    System.out.println("Error....!!!");
                }scan.nextLine();
                break;
            case 2:
                System.out.print("Enter the user id\t");
                size=dobj.delete_user(arr2,size);
                scan.nextLine();
                break;
            case 3:
                
                System.out.print("Enter id for modification\t");
                checkid = scan.nextInt();                
                for(i =0;i<size;i++)
                {
                    if(checkid==arr2[i].id)
                    
                    {
                        arr2[i].modify();
                        break;
                    }
                }
                if(i==size)
                    System.out.println("Id not found");  
                scan.nextLine();
                break;
            case 4:
               System.out.print("Enter the id for display\t");
                checkid = scan.nextInt();
                for(i =0;i<size;i++)
                {
                    if(checkid==arr2[i].id)
                    {arr2[i].display_user_record();   
                    break;}
                }
                if(i==size)
                    System.out.println("Id not found");  
                scan.nextLine();
                break;
            case 5:
                System.out.print("Enter the id\t");
                checkid= scan.nextInt();
                for(i=0;i<size;i++)
                {
                    if(checkid==arr2[i].id)
                    {
                        arr2[i].display_balance(checkid);
                        break;
                    }
                }
                if(i==size)
                    System.out.println("Id not found");
                break;
           
            case 6:
                   System.out.print("Enter the id\t");
                checkid= scan.nextInt();
                for(i=0;i<size;i++)
                {
                    if(checkid==arr2[i].id)
                    {
                        System.out.print("Enter the amount to be debited\t");
                        amount = scan.nextDouble();
                        arr2[i].debit_amount(amount);
                        break;
                    }
                }
                if(i==size)
                    System.out.println("Id not found");
                break;
            case 7:
                System.out.println("Compile again");
                break;
            default:
                System.out.println("Entered Wrong choice"); 
                break;
        } 
        System.out.println("Press (Y/N) to continue");
        ch='n';
        ch = scan.next().charAt(0);
        }
       catch(Exception e)
       {
           System.out.println("Wrong input the program is terminating");
           System.exit(0);
       }}while(ch=='Y'||ch=='y');
        }
        catch(Exception e)
        {
            System.out.println("Sorry the program is terminated due to some wrong input");
        }
    }
           else System.out.println("Entered Wrong Choice");
         }
         catch(Exception e)
         {
             System.out.println("Invalid input");
         }
    }
       
}
