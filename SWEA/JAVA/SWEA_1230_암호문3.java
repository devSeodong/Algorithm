package LinkedList;

import java.util.Scanner;

class Node {
	int data;
	Node link;

	Node(int data, Node link) {
		this.data = data;
		this.link = link;
	}
}

class List {
	// 리스트의 시작위치에 해당하는 노드의 주소, 더비노드
	Node head;
	// 리스트의 마지막위치에 해당하는 노드의 주소, 더미노드
	Node tail;

	List() {
		tail = new Node(-1, null);
		head = new Node(-1, tail);
	}

	// 앞에서부터 x번째 노드를 찾는 메서드
	Node index(int x) {
		// 앞에서부터 x번째 찾기 위해 제일 앞에 있는 head부터 출발
		Node pos = head;
		for (int i = 0; i < x; i++) {
			pos = pos.link;
		}
		return pos;
	}

	void addLast(int[] nums) {
		for (int num : nums) {
			// 원래 tail을 새데이터로 바꿈
			// 그 뒤에 tail을 이어붙이기
			tail.data = num;
			Node newNode = new Node(-1, null); // newNode를 tail로
			tail.link = newNode;
			tail = newNode;
		}
	}

	void insert(int x, int[] nums) {
		// pos : x번째 노드
		Node pos = index(x);

		for (int num : nums) {
			// 삽입할 노드
			Node newNode = new Node(num, pos.link);
			pos.link = newNode;
			pos = newNode;
		}
	}

	// x번째 노드 뒤에 있는 y개의 노드 삭제
	// x번째 노드가 x+y+1번째를 가리키게 만들면
	// 그 사이에 있는 y개의 노드가 삭제됨
	void remove(int x, int y) {
		// pos : x번째 노드
		Node pos = index(x);

		// x + y + 1번째 노드를 찾는 과정
		Node nextNode = pos.link;
		for (int i = 0; i < y; i++) {
			nextNode = nextNode.link;
		}
		pos.link = nextNode;
	}

	// method for debugging
	void printList() {
		Node pos = head.link;
		for(int i=0; i<10; i++) {
			System.out.println(pos.data + " ");
			pos = pos.link;
		}
		System.out.println();
	}

}

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int N = sc.nextInt();
			int[] initNums = new int[N];
			for (int i = 0; i < N; i++) {
				initNums[i] = sc.nextInt();
			}

			// 암호문 초기상태 만들기
			List list = new List();
			list.addLast(initNums);

			// 명령 처리하기
			int M = sc.nextInt();
			for (int m = 0; m < M; m++) {
				char op = sc.next().charAt(0);
				if (op == 'I') {
					int x = sc.nextInt();
					int y = sc.nextInt();
					int[] nums = new int[y];
					for (int i = 0; i < y; i++) {
						nums[i] = sc.nextInt();
					}
					list.insert(x, nums);
				} else if (op == 'D') {
					int x = sc.nextInt();
					int y = sc.nextInt();
					list.remove(x, y);
				} else {
					int y = sc.nextInt();
					int[] nums = new int[y];
					for (int i = 0; i < y; i++) {
						nums[i] = sc.nextInt();
					}
					list.addLast(nums);
				}
			}
			
			System.out.print("#"+t+" ");
			list.printList();
		}
	}
}
