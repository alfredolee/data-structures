package main

import (
	"fmt"

	array "./Array"
)

func main() {
	arr := array.New(10)
	for i := 0; i < 10; i++ {
		arr.AddLast(i)
	}
	fmt.Println(arr)

	arr.Add(1, 100)
	fmt.Println(arr)

	arr.AddFirst(-1)
	fmt.Println(arr)

	arr.Remove(2)
	fmt.Println(arr)

	arr.RemoveLast()
	fmt.Println(arr)

	arr.RemoveFirst()
	fmt.Println(arr)

	arr.RemoveElement(0)
	fmt.Println(arr)

	fmt.Println("arr中是否包含数字1: ", arr.Contains(1))
	fmt.Println("arr中数字1的首次出现位置: ", arr.Find(1))
	arr.AddLast(1)
	fmt.Println("arr中数字1的所有出现位置: ", arr.FindAll(1))

	for i := 0; i < 4; i++ {
		arr.RemoveFirst()
	}
	fmt.Println(arr)
}
