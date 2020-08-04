/**
 * Create build list from list of lib dependencies
 *
 * 1. Find libs with no dependencies, add them to build list
 * 2. Delete all libs from 1 in all dependencies
 * 3. Goto 1 or if build list size = lib list size -> job is done
 */

fun main() {
    // Prepare graph
    val nodeA = GraphNode("A")
    val nodeB = GraphNode("B")
    val nodeC = GraphNode("C")
    val nodeD = GraphNode("D")
    nodeA.link(nodeB) // lib A depends on lib B
    nodeB.link(nodeC)
    nodeA.link(nodeC)
    // Prepare dependency list
    val nodes = mutableListOf(nodeA, nodeB, nodeC, nodeD)
    // Get build list and print it
    val orderedNodes = getBuildList(nodes)
    orderedNodes.forEach { node -> println(node)} // C, D, B, A
}

// Returns build list from dependency list
fun getBuildList(nodes: List<GraphNode<String>>): List<GraphNode<String>> {
    val orderedNodes = mutableListOf<GraphNode<String>>()
    // while not all nodes in build list
    while(orderedNodes.size < nodes.size) {
        nodes.forEach { node ->
            // if node has no dependency...
            if (!orderedNodes.contains(node) && node.children.isEmpty()) {
                // it go to build list
                orderedNodes.add(node)
                // all dependencies on it to remove
                deleteChild(nodes, node)
            }
        }
    }
    return orderedNodes
}

// Delete all dependencies from lib, added to build list
fun deleteChild(nodes: List<GraphNode<String>>, childRemoving: GraphNode<String>) {
    nodes.forEach {node ->
        node.children.removeAll { child -> child == childRemoving }
    }
}
