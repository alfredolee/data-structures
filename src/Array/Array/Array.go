package array

import (
	"bytes"
	"fmt"
	"reflect"
)

// Array struct
type Array struct {
	data []interface{}
	size int
}

// New 构造函数
// capacity 数组容量
func New(capacity int) *Array {
	return &Array{
		data: make([]interface{}, capacity),
	}
}

// GetCapacity 获取数组的容量
func (thi *Array) GetCapacity() int {
	return len(thi.data)
}

// GetSize 获取数组元素个数
func (thi *Array) GetSize() int {
	return thi.size
}

// isEmpty 判断数组是否为空
func (thi *Array) isEmpty() bool {
	return thi.size == 0
}

// Get 获取数组 index 索引位置的元素
func (thi *Array) Get(index int) interface{} {
	if index < 0 || index >= thi.size {
		panic("Get Failed. Index is illegal.")
	}
	return thi.data[index]
}

// Set 修改 index 索引位置的元素为 val
func (thi *Array) Set(index int, val interface{}) {
	if index < 0 || index >= thi.size {
		panic("Get Failed. Index is illegal.")
	}
	thi.data[index] = val
}

// Add 在 index 索引位置添加一个元素 val
func (thi *Array) Add(index int, val interface{}) {
	if index < 0 || index > thi.size {
		panic("Add failed. Require index >= 0 and index <= size.")
	}

	if thi.size == len(thi.data) {
		thi.resize(thi.size * 2)
	}

	for i := thi.size - 1; i >= index; i-- {
		thi.data[i+1] = thi.data[i]
	}

	thi.data[index] = val
	thi.size++
}

// AddLast 数组尾部追加元素
func (thi *Array) AddLast(val interface{}) {
	thi.Add(thi.size, val)
}

// AddFirst 数组头部插入元素
func (thi *Array) AddFirst(val interface{}) {
	thi.Add(0, val)
}

// Remove 移除 index 索引位置的元素
func (thi *Array) Remove(index int) interface{} {
	if index < 0 || index >= thi.size {
		panic("Remove failed,Index is illegal.")
	}

	item := thi.data[index]
	for i := index; i < thi.size; i++ {
		thi.data[i] = thi.data[i+1]
	}

	thi.size--
	thi.data[thi.size] = nil //loitering object != memory leak

	if thi.size == len(thi.data)/4 && len(thi.data)/2 != 0 {
		thi.resize(len(thi.data) / 2)
	}

	return item
}

// RemoveLast 移除数组最后一个元素
func (thi *Array) RemoveLast() interface{} {
	return thi.Remove(thi.size - 1)
}

// RemoveFirst 移除数组第一个元素
func (thi *Array) RemoveFirst() interface{} {
	return thi.Remove(0)
}

// RemoveElement 移除数组中的元素 val
func (thi *Array) RemoveElement(val interface{}) bool {
	index := thi.Find(val)
	if index == -1 {
		return false
	}
	thi.Remove(index)
	return true
}

// Contains 查询数组中是否包含元素 val
func (thi *Array) Contains(val interface{}) bool {
	for i := 0; i < thi.size; i++ {
		if reflect.DeepEqual(thi.data[i], val) {
			return true
		}
	}

	return false
}

// Find 查找数组中元素 val 的索引
func (thi *Array) Find(val interface{}) int {
	for i := 0; i < thi.size; i++ {
		if reflect.DeepEqual(thi.data[i], val) {
			return i
		}
	}
	return -1
}

// FindAll 查找数组中元素 val 的所有索引值
func (thi *Array) FindAll(val interface{}) (indexes []int) {
	for i := 0; i < thi.size; i++ {
		if reflect.DeepEqual(thi.data[i], val) {
			indexes = append(indexes, i)
		}
	}
	return
}

func (thi *Array) resize(newCapacity int) {
	newData := make([]interface{}, newCapacity)
	for i := 0; i < thi.size; i++ {
		newData[i] = thi.data[i]
	}
	thi.data = newData
}

func (thi *Array) String() string {
	var buffer bytes.Buffer

	buffer.WriteString(fmt.Sprintf("Array: size = %d, capacity = %d\n", thi.size, len(thi.data)))
	buffer.WriteString("[")
	for i := 0; i < thi.size; i++ {
		buffer.WriteString(fmt.Sprint(thi.data[i]))
		if i != thi.size-1 {
			buffer.WriteString(", ")
		}
	}
	buffer.WriteString("]")

	return buffer.String()
}
