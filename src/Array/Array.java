package Array;

public class Array<T> {
    private T[] data;
    private int size;

    /**
     * 构造函数， 传入数组的容量capacity构造Array
     *
     * @param capacity, 数组容量
     */
    public Array(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认容量capacity=10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组的元素个数
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     *
     * @return data.length
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return size == 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取index索引位置的元素
     *
     * @param index, 索引
     * @return 元素
     */
    T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 设置index索引的元素的值为e
     *
     * @param index, 索引
     * @param e,     值
     */
    void set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 向数组尾部添加元素
     *
     * @param e, 添加的数组元素
     */
    public void push(T e) {
        this.insert(size, e);
    }

    /**
     * 向数组首位添加元素
     *
     * @param e, 添加的数组元素
     */
    public void unshift(T e) {
        insert(0, e);
    }

    /**
     * 在第index个位置插入新元素e
     *
     * @param index, 索引
     * @param e,     待添加的元素
     */
    public void insert(int index, T e) {


        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index < size.");
        }

        if (size == data.length) {
            this.resize(data.length * 2);
        }

        int i = size - 1;
        while (i >= index) {
            data[i + 1] = data[i];
            i--;
        }

        data[index] = e;
        size++;
    }

    /**
     * 删除索引位置元素
     *
     * @param index, 索引值
     * @return 删除的元素
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        T ret = data[index];

        int i = index + 1;
        while (i < size) {
            data[i - 1] = data[i];
            i++;
        }

        size--;
        data[size] = null; // loitering objects != memory leak

        if (size == data.length / 4 && data.length / 2 != 0) {
            this.resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除数组中的匹配e的第一个元素
     *
     * @param e, 待删除的元素
     * @return 是否删除成功，不存在则删除失败
     */
    public boolean removeElement(T e) {
        int index = this.findIndex(e);

        if (index != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    /**
     * 删除数组第一个元素
     *
     * @return 数组第一个元素
     */
    public T shift() {
        return this.remove(0);
    }

    /**
     * 删除数组最后一个元素
     *
     * @return 数组最后一个元素
     */
    public T pop() {
        return this.remove(size - 1);
    }

    /**
     * 查看数组是否包含元素e
     *
     * @param e, 要查找的元素
     * @return booloean, 是否存在
     */
    public boolean includes(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素在数组中的索引
     *
     * @param e, 待查找的元素
     * @return 存在, 返回索引i; 反之, 返回-1
     */
    public int findIndex(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}

