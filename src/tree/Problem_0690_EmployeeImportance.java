package tree;

import java.util.*;

//690. 员工的重要性
public class Problem_0690_EmployeeImportance {
    // Definition for Employee.
    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> idToEmp = idToEmp(id, employees);
        Employee root = idToEmp.get(id);
        Queue<Employee> q = new LinkedList<>();
        q.offer(root);
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Employee emp = q.poll(); //出一个，进一批(poll节点的所有子节点)
                ans += emp.importance;
                for (int k : emp.subordinates) {
                    Employee employee = idToEmp.get(k);
                    q.offer(employee);
                }
            }
        }
        return ans;
    }

    public Map<Integer, Employee> idToEmp(int id, List<Employee> employees) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return map;
    }

}
