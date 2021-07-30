type LRUCache struct {
	items map[int]*node
	root  *node
	len   int
	cap   int
}

type node struct {
	key        int
	value      int
	prev, next *node
}

func Constructor(capacity int) LRUCache {
	root := new(node)
	root.prev = root
	root.next = root
	return LRUCache{
		items: make(map[int]*node),
		root:  root,
		len:   0,
		cap:   capacity,
	}
}

func (this *LRUCache) Get(key int) int {
	if n, ok := this.items[key]; ok {
		this.PopNode(n)
		this.pushBack(n.key, n.value)
        return n.value
	}
	return -1
}

func (this *LRUCache) Put(key, value int) {
	if this.cap == this.len && this.Get(key) == -1 {
		this.PopNode(this.root.next)
	}

	if n, ok := this.items[key]; ok {
		this.PopNode(n)
	}
	this.pushBack(key, value)
}

func (this *LRUCache) PopNode(n *node) {
	n.prev.next = n.next
	n.next.prev = n.prev
	this.len = this.len - 1
	delete(this.items, n.key)
}

func (this *LRUCache) pushBack(key, value int) {
	n := &node{
		key:   key,
		value: value,
		prev:  this.root.prev,
		next:  this.root,
	}
	this.root.prev.next = n
	this.root.prev = n
	this.items[key] = n
	this.len = this.len + 1
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */