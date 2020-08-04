class GraphNode<T> (val data: T) {

    val children: MutableList<GraphNode<T>> = mutableListOf()

    fun link(node: GraphNode<T>) {
        children.add(node)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as GraphNode<*>
        if (data != other.data) return false
        return true
    }

    override fun hashCode(): Int {
        return data?.hashCode() ?: 0
    }

    override fun toString(): String {
        return data.toString()
    }

}