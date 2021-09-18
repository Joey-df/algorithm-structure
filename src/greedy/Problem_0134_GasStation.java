package greedy;

//134. 加油站
//https://leetcode-cn.com/problems/gas-station/
public class Problem_0134_GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas==null || cost==null || gas.length!=cost.length) {
            return -1;
        }
        int n = gas.length;
        int tank = 0;
        int start = 0;
        int gasSum = 0;
        int costSum = 0;
        for (int i = 0; i < n; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            tank += gas[i]-cost[i];
            if (tank < 0) {
                tank = 0;
                start = i+1;
            }
        }
        return gasSum<costSum ? -1 : start;
    }

}
