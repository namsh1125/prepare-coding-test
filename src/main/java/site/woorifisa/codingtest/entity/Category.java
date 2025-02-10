package site.woorifisa.codingtest.entity;

import lombok.Getter;

@Getter
public enum Category {
    BACKTRACKING("백트래킹"),
    BINARY_SEARCH("이분 탐색"),
    BRUTE_FORCE("완전탐색"),
    DATA_STRUCTURE("자료구조"),
    DISJOINT_SET("분리집합"),
    DIVIDE_AND_CONQUER("분할정복"),
    DYNAMIC_PROGRAMMING("동적계획법"),
    GRAPH_TRAVERSAL("그래프 탐색"),
    GREEDY("탐욕법"),
    IMPLEMENTATION("구현"),
    MATH("수학"),
    MINIMUM_SPANNING_TREE("최소 신장 트리"),
    PREFIX_SUM("누적 합"),
    SHORTEST_PATH("최단 경로"),
    SIMULATION("시뮬레이션"),
    STRING("문자열"),
    TOPOLOGICAL_SORT("위상 정렬"),
    TREE("트리"),
    TRIE("트라이"),
    TWO_POINTER("투 포인터");

    private final String name;

    Category(String name) {
        this.name = name;
    }

}
