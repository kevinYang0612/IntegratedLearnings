import java.util.Arrays;
import java.util.Random;
public class MergeSortArrayOfObject
{
    public static void main(String[] args)
    {
        // Exercise 1:
        Random rand = new Random();
        int[] intArray = new int[20];
        for (int i = 0; i < intArray.length; i++)
        {
            int randInt = rand.nextInt(100);
            intArray[i] = randInt;
        }
        // System.out.println(Arrays.toString(intArray)); // for compare
        traversePrint(intArray);
        // Arrays.sort(intArray);
        // System.out.println(Arrays.toString(intArray));
        mergeSort(intArray);
        System.out.println("Ascending order of integer array: ");
        traversePrint(intArray);

        // Exercise 2:
        double[] doubleArray = new double[10];

        for (int i = 0; i < doubleArray.length; i++)
        {
            double randDouble = Math.random();
            int randInt = rand.nextInt(100);
            doubleArray[i] = randDouble * randInt;
        }
        // System.out.println(Arrays.toString(doubleArray));
        traversePrint2(doubleArray);
        mergeBackward(doubleArray);
        System.out.println("descending order of double array: ");
        traversePrint2(doubleArray);

        // Exercise 3:
        Item[] items = new Item[10];
        int i = 0;
        while (i < items.length)
        {
            items[i] = new Item();
            // traverse print out
            System.out.println(items[i].toString());
            i++;
        }
        mergeSortItem(items);
        System.out.println("sort items by ascending order of quantity in stock: ");
        for (int j = 0; j < items.length; j++)
        {
            System.out.println(items[j]);
        }

    }
    public static void mergeSortItem(Item[] itemsArray)
    {
        if (itemsArray.length >= 2)
        {
            Item[] theLeft = Arrays.copyOfRange(itemsArray, 0,
                    itemsArray.length / 2);
            Item[] theRight = Arrays.copyOfRange(itemsArray,
                    itemsArray.length / 2, itemsArray.length);
            mergeSortItem(theLeft);
            mergeSortItem(theRight);
            mergeItem(itemsArray, theLeft, theRight);
        }
    }

    private static void mergeItem(Item[] theResult, Item[] theLeft, Item[] theRight)
    {
        int left = 0;  // index into left array
        int right = 0;  // index into right array

        for (int i = 0; i < theResult.length; i++)
        {
            if (right >= theRight.length ||
                    (left < theLeft.length &&
                            theLeft[left].getMyQuantity() <= theRight[right].getMyQuantity()))
            {
                theResult[i] = theLeft[left];
                left++;
            }
            else
            {
                theResult[i] = theRight[right];
                right++;
            }
        }
    }
    public static void traversePrint(int[] intArray)
    {
        // fence post
        System.out.print("This is random integer array: {" + intArray[0] + ", ");
        for (int i = 1; i < intArray.length - 1; i++)
        {
            System.out.print(intArray[i] + ", ");
        }
        System.out.println(intArray[intArray.length - 1] + "}");
    }
    public static void traversePrint2(double[] doubleArray)
    {
        // fence post
        System.out.print("This is random double array: " + "{" + doubleArray[0] + ", ");
        for (int i = 1; i < doubleArray.length - 1; i++)
        {
            System.out.print(doubleArray[i] + ", ");
        }
        System.out.println(doubleArray[doubleArray.length - 1] + "}");
    }

    public static void mergeSort(int[] intArray)
    {
        if (intArray.length >= 2)
        {
            int[] theLeft = Arrays.copyOfRange(intArray, 0,
                    intArray.length / 2);
            int[] theRight = Arrays.copyOfRange(intArray,
                    intArray.length / 2, intArray.length);
            mergeSort(theLeft);
            mergeSort(theRight);
            merge(intArray, theLeft, theRight);
        }
    }

    private static void merge(int[] theResult, int[] theLeft, int[] theRight)
    {
        int left = 0;  // index into left array
        int right = 0;  // index into right array

        for (int i = 0; i < theResult.length; i++)
        {
            if (right >= theRight.length ||
                    (left < theLeft.length && theLeft[left] <= theRight[right]))
            {
                theResult[i] = theLeft[left];
                left++;
            }
            else
            {
                theResult[i] = theRight[right];
                right++;
            }
        }
    }
    public static void mergeBackward(double[] doubleArray)
    {
        if (doubleArray.length >= 2)
        {
            double[] theLeft = Arrays.copyOfRange(doubleArray, 0,
                    doubleArray.length / 2);
            double[] theRight = Arrays.copyOfRange(doubleArray,
                    doubleArray.length / 2, doubleArray.length);
            mergeBackward(theLeft);
            mergeBackward(theRight);
            merge2(doubleArray, theLeft, theRight);
        }
    }
    private static void merge2(double[] doubleArray, double[] theLeft, double[] theRight)
    {
        int left = 0;
        int right = 0;

        for (int i = 0; i < doubleArray.length; i++)
        {
            if (right >= theRight.length ||
                    (left < theLeft.length) && theLeft[left] >= theRight[right])
            {
                doubleArray[i] = theLeft[left];
                left++;
            }
            else
            {
                doubleArray[i] = theRight[right];
                right++;
            }
        }
    }

}
class Item
{
    private String myName;
    private int myQuantity;
    private double myPrice;
    private static int myItem = 0;

    public Item()
    {
        myName = "Item #0" + ++myItem;
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        myQuantity = randInt;
        double randDouble = Math.random();
        myPrice = randInt * randDouble;
    }


    @Override
    public String toString() {
        return "Item: {" +
                "myName = '" + myName + '\'' +
                ", myQuantity = " + myQuantity +
                ", myPrice = " + myPrice +
                '}';
    }

    public int getMyQuantity()
    {
        return myQuantity;
    }
}
