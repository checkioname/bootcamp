package Models;

import java.util.Map;
import java.util.List;

public class Closet {
    private Map<Integer, List<Clothes>> repo;
    private Integer counter;


    public Integer storeClothes(List<Clothes> clothesList) {
        int idx = this.repo.size() + 1;
        this.repo.put(idx, clothesList);
        return idx;
    }

    public void showClothes() {
        for (var entry: repo.entrySet()) {
            System.out.printf("Peças da chave - %v\n", entry.getKey());
            for (var value : entry.getValue()) {
                System.out.println(value);
            }
        }
    }

    public List<Clothes> getClothes(Integer number) {
        if (!this.repo.containsKey(number)) {
            System.out.println("A chave informada não existe");
            return new List<Clothes>{};
        }
        return this.repo.get(number);
    }

}
