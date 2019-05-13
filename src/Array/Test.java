package Array;

public class Test {

    public static void main(String[] args) {

        Array<Integer> arr = new Array<Integer>();
        for (int i = 0; i < 10; i++) {
            arr.push(i);
        }
        System.out.println(arr);

        arr.insert(2, 100);
        System.out.println(arr);

        arr.unshift(-1);
        System.out.println(arr);

        arr.remove(3);
        System.out.println(arr);

        arr.removeElement(3);
        System.out.println(arr);

        arr.shift();
        System.out.println(arr);

        arr.pop();
        System.out.println(arr);

        System.out.println(arr.get(0));

        arr.set(0, 3);
        System.out.println(arr);

        System.out.println(arr.includes(88));

        System.out.println(arr.getSize());

        System.out.println(arr.getCapacity());

        System.out.println(arr.isEmpty());
    }
}
