 /* name: ENA-13 Bar-code Generator *
 * author: XVZH from CCNU          *
 * time:2016-05-04                 *
 * all right reserved              */

import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class BinaryNumGenerator {
	int data[];
	public BinaryNumGenerator(int data[]) {
		this.data = data;
	}
	//int data[] = {7,9,0,1,2,3,4,5,6,7,8,9,2};      //��������
	
	public List<int[]> getBinaryCodes() {
		List<int[]> binaryCodes = new LinkedList<int[]>();  //���������洢���ɵĶ��������ݶ�
		int[] beginCode = {1,0,1}; 
		int[] middleCode = {0,1,0,1,0};
		int[] checkCode = right_BinaryGenerator(12);
		int[] endCode = {1,0,1};
		binaryCodes.add(beginCode); //�����ʼ��
		/*��������������������*/
		for(int i=1; i<=6; i++) {
			binaryCodes.add(left_BinaryGenerator(data[0], i));
		}
		binaryCodes.add(middleCode);  //����м�ָ�����������
		/*����Ҳ���������������*/
		for(int i=7; i<=11; i++) {
			binaryCodes.add(right_BinaryGenerator(i));
		}
		binaryCodes.add(checkCode);   //���У���
		binaryCodes.add(endCode);    //�����ֹ��
		return binaryCodes;
	}
	/*A��ӳ���*/
	private int[] typeA_mapping(int num) {
		int num0[] = {0,0,0,1,1,0,1};
		int num1[] = {0,0,1,1,0,0,1};
		int num2[] = {0,0,1,0,0,1,1};
		int num3[] = {0,1,1,1,1,0,1};
		int num4[] = {0,1,0,0,0,1,1};
		int num5[] = {0,1,1,0,0,0,1};
		int num6[] = {0,1,0,1,1,1,1};
		int num7[] = {0,1,1,1,0,1,1};
		int num8[] = {0,1,1,0,1,1,1};
		int num9[] = {0,0,0,1,0,1,1};
		int num_error[] = {1,1,1,1,1,1,1};
		switch (num) {
		case 0:
			return num0;
		case 1:
			return num1;
		case 2:
			return num2;
		case 3:
			return num3;
		case 4:
			return num4;
		case 5:
			return num5;
		case 6:
			return num6;
		case 7:
			return num7;
		case 8:
			return num8;
		case 9:
			return num9;
		default:
			System.out.println("A���ַ���ӳ�����");
			return num_error;
		}
	}
	/*B��ӳ���*/
	private int[] typeB_mapping(int num) {
		int num0[] = {0,1,0,0,1,1,1};
		int num1[] = {0,1,1,0,0,1,1};
		int num2[] = {0,0,1,1,0,1,1};
		int num3[] = {0,1,0,0,0,0,1};
		int num4[] = {0,0,1,1,1,0,1};
		int num5[] = {0,1,1,1,0,0,1};
		int num6[] = {0,0,0,0,1,0,1};
		int num7[] = {0,0,1,0,0,0,1};
		int num8[] = {0,0,0,1,0,0,1};
		int num9[] = {0,0,1,0,1,1,1};
		int num_error[] = {1,1,1,1,1,1,1};
		switch (num) {
		case 0:
			return num0;
		case 1:
			return num1;
		case 2:
			return num2;
		case 3:
			return num3;
		case 4:
			return num4;
		case 5:
			return num5;
		case 6:
			return num6;
		case 7:
			return num7;
		case 8:
			return num8;
		case 9:
			return num9;
		default:
			System.out.println("B���ַ���ӳ�����");
			return num_error;
		}
	}
	/*C��ӳ���*/
	private int[] typeC_mapping(int num) {
		int num0[] = {1,1,1,0,0,1,0};
		int num1[] = {1,1,0,0,1,1,0};
		int num2[] = {1,1,0,1,1,0,0};
		int num3[] = {1,0,0,0,0,1,0};
		int num4[] = {1,0,1,1,1,0,0};
		int num5[] = {1,0,0,1,1,1,0};
		int num6[] = {1,0,1,0,0,0,0};
		int num7[] = {1,0,0,0,1,0,0};
		int num8[] = {1,0,0,1,0,0,0};
		int num9[] = {1,1,1,0,1,0,0};
		int num_error[] = {1,1,1,1,1,1,1};
		switch (num) {
		case 0:
			return num0;
		case 1:
			return num1;
		case 2:
			return num2;
		case 3:
			return num3;
		case 4:
			return num4;
		case 5:
			return num5;
		case 6:
			return num6;
		case 7:
			return num7;
		case 8:
			return num8;
		case 9:
			return num9;
		default:
			System.out.println("C���ַ���ӳ�����");
			return num_error;
		}
	}
	
	private int[] right_BinaryGenerator(int index) {
		return typeC_mapping(data[index]);
	}
	
	private int[] left_BinaryGenerator(int firstNum, int index) {
		switch (firstNum) {
		case 0:
			switch (index) {
			default:
				return typeA_mapping(data[index]);
			}
		case 1:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeA_mapping(data[index]);
			case 3:
				return typeB_mapping(data[index]);
			case 4:
				return typeA_mapping(data[index]);
			case 5:
				return typeB_mapping(data[index]);
			case 6:
				return typeB_mapping(data[index]);
			default:
				break;
			}
		case 2:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeA_mapping(data[index]);
			case 3:
				return typeB_mapping(data[index]);
			case 4:
				return typeB_mapping(data[index]);
			case 5:
				return typeA_mapping(data[index]);
			case 6:
				return typeB_mapping(data[index]);
			default:
				break;
			}
		case 3:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeA_mapping(data[index]);
			case 3:
				return typeB_mapping(data[index]);
			case 4:
				return typeB_mapping(data[index]);
			case 5:
				return typeB_mapping(data[index]);
			case 6:
				return typeA_mapping(data[index]);
			default:
				break;
			}
		case 4:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeB_mapping(data[index]);
			case 3:
				return typeA_mapping(data[index]);
			case 4:
				return typeA_mapping(data[index]);
			case 5:
				return typeB_mapping(data[index]);
			case 6:
				return typeB_mapping(data[index]);
			default:
				break;
			}
		case 5:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeB_mapping(data[index]);
			case 3:
				return typeB_mapping(data[index]);
			case 4:
				return typeA_mapping(data[index]);
			case 5:
				return typeA_mapping(data[index]);
			case 6:
				return typeB_mapping(data[index]);
			default:
				break;
			}
		case 6:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeB_mapping(data[index]);
			case 3:
				return typeB_mapping(data[index]);
			case 4:
				return typeB_mapping(data[index]);
			case 5:
				return typeA_mapping(data[index]);
			case 6:
				return typeA_mapping(data[index]);
			default:
				break;
			}
		case 7:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeB_mapping(data[index]);
			case 3:
				return typeA_mapping(data[index]);
			case 4:
				return typeB_mapping(data[index]);
			case 5:
				return typeA_mapping(data[index]);
			case 6:
				return typeB_mapping(data[index]);
			default:
				break;
			}
		case 8:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeB_mapping(data[index]);
			case 3:
				return typeA_mapping(data[index]);
			case 4:
				return typeB_mapping(data[index]);
			case 5:
				return typeB_mapping(data[index]);
			case 6:
				return typeA_mapping(data[index]);
			default:
				break;
			}
		case 9:
			switch (index) {
			case 1:
				return typeA_mapping(data[index]);
			case 2:
				return typeB_mapping(data[index]);
			case 3:
				return typeB_mapping(data[index]);
			case 4:
				return typeA_mapping(data[index]);
			case 5:
				return typeB_mapping(data[index]);
			case 6:
				return typeA_mapping(data[index]);
			default:
				break;
			}
		default:
			System.out.println("��������ַ���ѡ�����");
			return typeA_mapping(data[index]);
		}
	}
	
}
