import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if (pa == pb)
                return false;

            if (rank[pa] < rank[pb])
                parent[pa] = pb;
            else if (rank[pb] < rank[pa])
                parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int maxStrength = 0;

        for (int[] e : edges)
            maxStrength = Math.max(maxStrength, e[2] * 2);

        int left = 1, right = maxStrength;
        int ans = -1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (canBuild(n, edges, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean canBuild(int n, int[][] edges, int k, int target) {

        DSU dsu = new DSU(n);
        int edgeCount = 0;
        int upgrades = 0;

        List<int[]> normal = new ArrayList<>();
        List<int[]> upgrade = new ArrayList<>();

        // Step 1: Process edges
        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int s = e[2];
            int must = e[3];

            if (must == 1) {

                if (s < target)
                    return false;

                if (!dsu.union(u, v))
                    return false;

                edgeCount++;
            } else {

                if (s >= target)
                    normal.add(e);

                else if (2 * s >= target)
                    upgrade.add(e);
            }
        }

        // Step 2: Use edges without upgrade
        for (int[] e : normal) {

            if (dsu.union(e[0], e[1])) {
                edgeCount++;
            }

            if (edgeCount == n - 1)
                return true;
        }

        // Step 3: Use edges with upgrade
        for (int[] e : upgrade) {

            if (upgrades == k)
                break;

            if (dsu.union(e[0], e[1])) {
                edgeCount++;
                upgrades++;
            }

            if (edgeCount == n - 1)
                return true;
        }

        return edgeCount == n - 1;
    }
}