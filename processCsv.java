import com.opencsv.bean.CsvBindByPosition;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ProcessCSV{



 public static void main(String[] args) throws IOException {

        String fileName = "c:\\test\\csv\\country.csv";

        List<User> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(User.class)
                .build()
                .parse();
		Collections.sort(beans);
		
		beans.stream()
         .collect(Collectors.toMap(
                  BinaryOperator.maxBy(Comparator.comparing(User::getVersion))))
         .values();
		 
        beans.forEach(System.out::println);

    }




	private class User implements Comparable<User>{

		@CsvBindByPosition(position = 0)
		private String userId;

		@CsvBindByPosition(position = 1)
		private String first;

		@CsvBindByPosition(position = 2)
		private String last;

		@CsvBindByPosition(position = 3)
		private Interger version;

		@CsvBindByPosition(position = 4)
		private String insurance;
		
		@Override
		public int compareTo(User u) {
			if (first() == null || u.first() == null) {
			  return 0;
			}
			return first().compareTo(u.first());
		}
	
	}

}