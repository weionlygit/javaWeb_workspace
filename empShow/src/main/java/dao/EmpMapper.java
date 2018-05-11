package dao;

import entity.Emp;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EmpMapper {
     List<Emp> listEmp();
//     插入数据返回的是条数 是int
     int saveEmp(@Param("emp")Emp emp);
     int deleteEmp(Integer id);

     Emp selectEmpById(Integer id);
     int updateEmp(@Param("emp")Emp emp);
}
