package ex3;

public class MyExceptionTest {

	public static void main(String[] args) {

		MyExceptionTest myE=new MyExceptionTest();


		try {
			myE.test();
		} catch (MyException e) {
			e.printStackTrace();
		}


	}

	void test() throws MyException{
		if(true){
			throw new MyException();
		}
	}

}
