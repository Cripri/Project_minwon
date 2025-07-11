package function.connector;

import java.util.List;

public class testcon {
    public static void main(String[] args) {
        Civil_Connector query = new Civil_Connector();
        query.start();

        QueryRequest<Members> req1 = new QueryRequest<>(
                "SELECT * FROM members",
                "findAllMembers",
                "Members",
                null,
                QueryType.SELECT,
                Members.class
        );

        try {
            query.putQuery(req1);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Members> allMembers = req1.getResultList();

        for(Members a : allMembers){
            System.out.println(a.toString());
        }
    }
}
