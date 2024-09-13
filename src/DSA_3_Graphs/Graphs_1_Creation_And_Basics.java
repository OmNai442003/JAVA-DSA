package DSA_3_Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
}
