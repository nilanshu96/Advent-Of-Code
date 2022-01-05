import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        BurrowState initialBurrowState = Util.readBurrowState("input.txt");
        BurrowState finalBurrowState = Util.getFinalBurrowState(initialBurrowState);
        
        Map<BurrowState,Integer> stateToCostMap = new HashMap<>();
        stateToCostMap.put(initialBurrowState,0);

        PriorityQueue<BurrowState> queue = new PriorityQueue<>();
        queue.offer(initialBurrowState);

        while(!queue.isEmpty()) {
            BurrowState currentBurrowState = queue.poll();

            //The cost of currentBurrowState may change in the below for loop
            //Hence its cost needs to be updated first before making changes
            currentBurrowState.setCost(Math.min(currentBurrowState.getCost(), stateToCostMap.getOrDefault(currentBurrowState, Integer.MAX_VALUE)));
            
            for(BurrowState state: currentBurrowState.getNextValidBurrowStates()) {
                //Energy cost of new states is calculated when they're being created based on their parent states (see BurrowState class)
                int cost = Math.min(state.getCost(), stateToCostMap.getOrDefault(state, Integer.MAX_VALUE));
                stateToCostMap.put(state, cost);
                state.setCost(cost);
                if(!queue.contains(state)) {
                    queue.offer(state);;
                }
            }
        }
        
        System.out.println("Answer: " + stateToCostMap.get(finalBurrowState));
    }
}