package DSA_3_Graphs;

import java.util.*;

public class Graphs_1_Creation_And_Basics {
    //    This will help for the creation of the vertex
    private static class Vertex {
        HashMap<String, Integer> nbrs = new HashMap<>();
    }

    //    This is the main part from where the list of connecting vertex will be there
//    A -> B, C
//    B -> A and so on
    HashMap<String, Vertex> vtces;

    //    This is the constructor which will be used
    public Graphs_1_Creation_And_Basics() {
        vtces = new HashMap<>();
    }

    //    Function :-
    public int numberOfVertex() {
        return this.vtces.size();
    }

    public boolean containsVertex(String vertexName) {
        return this.vtces.containsKey(vertexName);
    }

    public void addVertex(String vertexName) {
        Vertex newVertex = new Vertex();
        vtces.put(vertexName, newVertex);
        System.out.println("Vertex added" + " " + vertexName);
    }

    public void removeVertex(String vName) {
//        We need to remove all the edges from where the vertex to be removed is present
        System.out.println("Remove vertex\n");
        Vertex vtx = vtces.get(vName);
        ArrayList<String> removeVertexConnectionKeys = new ArrayList<>(vtx.nbrs.keySet());
        for (String key : removeVertexConnectionKeys) {
            Vertex connectionVertex = vtces.get(key);
            connectionVertex.nbrs.remove(vName);
        }
        vtces.remove(vName);
    }

    public int numberOfEdges() {
        int count = 0;
        ArrayList<String> keys = new ArrayList<>(this.vtces.keySet());
        for (String key : keys) {
            Vertex vtx = vtces.get(key);
            count += vtx.nbrs.size();
        }
        return count / 2;
    }

    public boolean containsEdge(String vName1, String vName2) {
        Vertex vtx1 = vtces.get(vName1);
        Vertex vtx2 = vtces.get(vName2);
        if (vtx1 == null || vtx2 == null) {
            return false;
        } else {
            return vtx1.nbrs.containsKey(vName2);
        }
    }

    public void addEdge(String vName1, String vName2, int cost) {
//        Check for the edge is present or not or the vertex exist or not
        Vertex vtx1 = vtces.get(vName1);
        Vertex vtx2 = vtces.get(vName2);
        if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vName2)) {
            return;
        }
        vtx1.nbrs.put(vName2, cost);
        vtx2.nbrs.put(vName1, cost);
        System.out.println("Edge added\t" + vName1 + " ----- " + vName2);
    }

    public void removeEdge(String vName1, String vName2) {
//        Get the list
//        If the edge or vertex only not exist doesn't exist only
        Vertex vtx1 = vtces.get(vName1);
        Vertex vtx2 = vtces.get(vName2);
        if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vName2)) {
            System.out.println("Edge deletion failed possible reasons :-");
            System.out.println("1) The given vertices not present.");
            System.out.println("2) The given edge not present.");
            return;
        }
        vtx1.nbrs.remove(vName2);
        vtx2.nbrs.remove(vName1);
    }

    public void display() {
        System.out.println("---------------------");
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
        for (String key : keys) {
            Vertex vtx = vtces.get(key);
            System.out.println(key + "\t: " + vtx.nbrs);
        }
        System.out.println("---------------------");
    }

    public boolean hasPath(String vertex1, String vertex2, HashMap<String, Boolean> processed) {
        processed.put(vertex1, true);
        if (containsEdge(vertex1, vertex2)) {
            return true;
        }
        Vertex givenVertexNeighbours = vtces.get(vertex1);
        ArrayList<String> givenVertexNeighboursList = new ArrayList<>(givenVertexNeighbours.nbrs.keySet());
        for (String key : givenVertexNeighboursList) {
            if (!processed.containsKey(key) && hasPath(key, vertex2, processed)) {
                return true;
            }
        }
        return false;
    }

    //    The below class will be used for the BFS algorithm
    private static class Pair {
        String vertexName;
        String pathSoFar;
    }

    public boolean BFSSearch(String src, String destination) {
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> queue = new LinkedList<>();
        Pair initialPair = new Pair();
        initialPair.vertexName = src;
        initialPair.pathSoFar = src;

//        Put the initial pair in the queue
        queue.addLast(initialPair);
//        Till the queue is not empty

        while (!queue.isEmpty()) {
            Pair removedPair = queue.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap
            if (processed.containsKey(removedPair.vertexName)) {
                continue;
//                Due to the usage of to continue all the below statements will not
//                execute and the execution goes on.
            }
//            Put in the processed Hashmap
            processed.put(removedPair.vertexName, true);
//            Check for the direct edge
            if (containsEdge(removedPair.vertexName, destination)) {
                System.out.println("Path -> " + removedPair.pathSoFar + destination);
                return true;
            }
//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
            Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
            ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
            for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                    Pair newPair = new Pair();
                    newPair.vertexName = nbr;
                    newPair.pathSoFar = removedPair.pathSoFar + nbr;
                    queue.addLast(newPair);
                }
            }
        }
        return false;
    }

    public boolean DFSSearch(String src, String destination) {
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> stack = new LinkedList<>();
        Pair initialPair = new Pair();
        initialPair.vertexName = src;
        initialPair.pathSoFar = src;

//        Put the initial pair in the queue
        stack.addFirst(initialPair);
//        Till the queue is not empty

        while (!stack.isEmpty()) {
            Pair removedPair = stack.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap
            if (processed.containsKey(removedPair.vertexName)) {
                continue;
//                Due to the usage of to continue all the below statements will not
//                execute and the execution goes on.
            }
//            Put in the processed Hashmap
            processed.put(removedPair.vertexName, true);
//            Check for the direct edge
            if (containsEdge(removedPair.vertexName, destination)) {
                System.out.println("Path -> " + removedPair.pathSoFar + destination);
                return true;
            }
//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
            Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
            ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
            for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                    Pair newPair = new Pair();
                    newPair.vertexName = nbr;
                    newPair.pathSoFar = removedPair.pathSoFar + nbr;
                    stack.addFirst(newPair);
                }
            }
        }
        return false;
    }

    public void BFT() {
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for (String key : keys) {
            if (processed.containsKey(key)){
                continue;
            }
            Pair initialPair = new Pair();
            initialPair.vertexName = key;
            initialPair.pathSoFar = key;

//        Put the initial pair in the queue
            queue.addLast(initialPair);
//        Till the queue is not empty
            while (!queue.isEmpty()) {
                Pair removedPair = queue.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap

                if (processed.containsKey(removedPair.vertexName)) {
                    continue;
//                Due to the usage of to continue all the below statements will not
//                execute and the execution goes on.
                }
//            Put in the processed Hashmap
                processed.put(removedPair.vertexName, true);

//            This is to print the path
                System.out.println(removedPair.vertexName + " " + removedPair.pathSoFar);

//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
                Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
                ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
                for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                    if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                        Pair newPair = new Pair();
                        newPair.vertexName = nbr;
                        newPair.pathSoFar = removedPair.pathSoFar + nbr;
                        queue.addLast(newPair);
                    }
                }
            }
        }
    }

    public void DFT() {
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> stack = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for (String key : keys) {
            if (processed.containsKey(key)){
                continue;
            }
            Pair initialPair = new Pair();
            initialPair.vertexName = key;
            initialPair.pathSoFar = key;

//        Put the initial pair in the queue
            stack.addFirst(initialPair);
//        Till the queue is not empty
            while (!stack.isEmpty()) {
                Pair removedPair = stack.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap

                if (processed.containsKey(removedPair.vertexName)) {
                    continue;
//                Due to the usage of to continue all the below statements will not
//                execute and the execution goes on.
                }
//            Put in the processed Hashmap
                processed.put(removedPair.vertexName, true);

//            This is to print the path
                System.out.println(removedPair.vertexName + " " + removedPair.pathSoFar);

//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
                Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
                ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
                for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                    if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                        Pair newPair = new Pair();
                        newPair.vertexName = nbr;
                        newPair.pathSoFar = removedPair.pathSoFar + nbr;
                        stack.addFirst(newPair);
                    }
                }
            }
        }
    }

    public boolean isCycle(){
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        for (String key : keys) {
            if (processed.containsKey(key)){
                continue;
            }
            Pair initialPair = new Pair();
            initialPair.vertexName = key;
            initialPair.pathSoFar = key;

//        Put the initial pair in the queue
            queue.addLast(initialPair);
//        Till the queue is not empty
            while (!queue.isEmpty()) {
                Pair removedPair = queue.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap

                if (processed.containsKey(removedPair.vertexName)) {
                    return true;
                }
//            Put in the processed Hashmap
                processed.put(removedPair.vertexName, true);

//            This is to print the path
//                System.out.println(removedPair.vertexName + " " + removedPair.pathSoFar);

//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
                Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
                ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
                for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                    if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                        Pair newPair = new Pair();
                        newPair.vertexName = nbr;
                        newPair.pathSoFar = removedPair.pathSoFar + nbr;
                        queue.addLast(newPair);
                    }
                }
            }
        }
        return false;
    }

    public boolean isConnected() {
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        int flag = 0;

        for (String key : keys) {
            if (processed.containsKey(key)){
                continue;
            }
            Pair initialPair = new Pair();
            initialPair.vertexName = key;
            initialPair.pathSoFar = key;

//            If there is no connection then the below code will only execute for once
            flag++;

//        Put the initial pair in the queue
            queue.addLast(initialPair);
//        Till the queue is not empty
            while (!queue.isEmpty()) {
                Pair removedPair = queue.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap

                if (processed.containsKey(removedPair.vertexName)) {
                    continue;
//                Due to the usage of to continue all the below statements will not
//                execute and the execution goes on.
                }
//            Put in the processed Hashmap
                processed.put(removedPair.vertexName, true);

//            This is to print the path
                System.out.println(removedPair.vertexName + " " + removedPair.pathSoFar);

//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
                Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
                ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
                for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                    if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                        Pair newPair = new Pair();
                        newPair.vertexName = nbr;
                        newPair.pathSoFar = removedPair.pathSoFar + nbr;
                        queue.addLast(newPair);
                    }
                }
            }
        }
        return !(flag >= 2);
    }

    public ArrayList<ArrayList<String>> getAllConnectedComponents() {
        HashMap<String, Boolean> processed = new HashMap<>();
//        This is for the queue purpose
        LinkedList<Pair> queue = new LinkedList<>();
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
//        To get all the connected components
        ArrayList<ArrayList<String>> allConnectedComponents = new ArrayList<>();

        for (String key : keys) {
            if (processed.containsKey(key)){
                continue;
            }
            Pair initialPair = new Pair();
            initialPair.vertexName = key;
            initialPair.pathSoFar = key;

//            If there is no connection then the below code will only execute for once
            ArrayList<String> newConnectedComponentGenerated = new ArrayList<>();

//        Put the initial pair in the queue
            queue.addLast(initialPair);
//        Till the queue is not empty
            while (!queue.isEmpty()) {
                Pair removedPair = queue.removeFirst();
//            If the node is already present in the processed Hashmap then no need to
//            keep that node again in the hashmap

                if (processed.containsKey(removedPair.vertexName)) {
                    continue;
//                Due to the usage of to continue all the below statements will not
//                execute and the execution goes on.
                }
//            Put in the processed Hashmap
                processed.put(removedPair.vertexName, true);

//              Add vertex new ArrayList
                newConnectedComponentGenerated.add(removedPair.vertexName);

//            If the direct edge is not there then go to the neighbours of that
//            and check for the path
                Vertex neighboursVertexChunkLocation = vtces.get(removedPair.vertexName);
                ArrayList<String> neighbours = new ArrayList<>(neighboursVertexChunkLocation.nbrs.keySet());
                for (String nbr : neighbours) {
//                If it is not processed then only add to the processed array
                    if (!processed.containsKey(nbr)) {
//                    Making the new pair of neighbour and put in the queue
                        Pair newPair = new Pair();
                        newPair.vertexName = nbr;
                        newPair.pathSoFar = removedPair.pathSoFar + nbr;
                        queue.addLast(newPair);
                    }
                }
            }
//            The final answer
            allConnectedComponents.add(newConnectedComponentGenerated);
        }
        return allConnectedComponents;
    }

//    This part will now be used for Kruskal Algorithm
    public static class DisjointSet{
//        To store the nodes name and the referencing address of it.
        HashMap<String, Node> map = new HashMap<>();
//        Node to store data
        private static class Node{
            String data;
            int rank; //Height
            Node parent; // Self - referencing
        }
//        Function required for disjoint DSA
        public void create(String value){
//            Node structure is required
            Node newNode = new Node();
            newNode.data = value;
//            Initial creation so rank would be zero
            newNode.rank = 0;
//            Self-referencing
            newNode.parent = newNode;
            map.put(value, newNode);
        }

//        Find operation
        public void union(String value1, String value2){
            Node set1 = map.get(value1);
            Node set2 = map.get(value2);

//            Now, we have to find parent of it
//            That is the representative element
            Node repElem1 = find(set1);
            Node repElem2 = find(set2);

//            Check that they are of same parent
//            To avoid null safety
            if (Objects.equals(repElem1.data, repElem2.data)){
                return;
            }
            else {
//                Rank changing
                if (repElem1.rank == repElem2.rank){
//                    Both have the same rank
                    repElem2.parent = repElem1;
                    repElem1.rank = repElem1.rank + 1;
                }
                else if (repElem1.rank > repElem2.rank){
                    repElem2.parent = repElem1;
                }
                else {
                    repElem1.parent = repElem2;
                }
            }
        }

//        Find the representative element
//        A
//       / \
//      B   C
//     /
//    D
//    D belongs to which set ?, So it gives representative element
//    Hashmap to store Node name and the address of it
        public String find(String value){
            return find(map.get(value)).data;
//            The storage is like...
//            "A" : 10058 Memory location
//            So, now A, data we need
        }

//        To obtain the node.
        private Node find(Node node){
//            When the node address and the node.parent is same
            if (node == node.parent){
                return node;
            }
            return find(node.parent);
        }
    }

    private static class EdgePair implements Comparable<EdgePair>{
        String value1;
        String value2;
        int cost;

        @Override
        public int compareTo(EdgePair e){
            return this.cost - e.cost;
        }

        public String toString(){
            return value1 + "-" + value2 + " : " + cost;
        }
    }

    private ArrayList<EdgePair> getAllEdges(){
        ArrayList<EdgePair> edgesList = new ArrayList<>();
        for (String vertexName : vtces.keySet()){
            Vertex vertex = vtces.get(vertexName);
            for (String nbr : vertex.nbrs.keySet()){
                EdgePair edgePair = new EdgePair();
                edgePair.value1 = vertexName;
                edgePair.value2 = nbr;
                edgePair.cost = vertex.nbrs.get(nbr);
                edgesList.add(edgePair);
            }
        }
        return edgesList;
    }

    public void kruskal(){
        ArrayList<EdgePair> edges = getAllEdges();
        Collections.sort(edges);
        System.out.println("The sorted edged :-" + edges + "\n");
//        Set creation
        DisjointSet set = new DisjointSet();
        for (String vertexName : vtces.keySet()){
            set.create(vertexName);
        }
//        Traverse edges
        for (EdgePair edgePair : edges){
//            Check the same set or different set
//            If belong to different set then do union else ignore
            String rep1 = set.find(edgePair.value1);
            String rep2 = set.find(edgePair.value2);

            if (rep1.equals(rep2)){
                continue;
            }
            else {
                System.out.println(edgePair);
                set.union(edgePair.value1, edgePair.value2);
            }
        }
    }
}
