package easy.sqlq;

/**
 * 2023/6/5
 *
 * @author x.z
 */
public class Solution181 {
    /**
     * 又一道SQL题
     * TODO 查询员工 select * from Employee where managerId is not null
     *      查询经理 select * from Employee where managerId is null
     *
     * TODO select t1.name as Employee from Employee t1
     * 	        left join Employee t2
     * 	        on t1.managerId = t2.id
     *        	where t2.id is not null
     * 	        and t1.salary > t2.salary
     * TODO answer : select A.name as Employee from employee A join employee B on A.managerId = B.id and A.salary > B.salary
     */
}
