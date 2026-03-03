package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.VegeDemo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vegetable")
public class VegeDemoController {

    private static List<VegeDemo> vegeList = new ArrayList<>();

    static {
        vegeList.add(new VegeDemo(1, "西红柿", 5.5, 100));
        vegeList.add(new VegeDemo(2, "黄瓜", 3.0, 150));
        vegeList.add(new VegeDemo(3, "土豆", 2.5, 200));
    }

    @GetMapping("/list")
    public List<VegeDemo> getVegeList() {
        return vegeList;
    }

    @GetMapping("/{id}")
    public VegeDemo getVegeById(@PathVariable Integer id) {
        return vegeList.stream()
                .filter(vege -> vege.getvName().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/add")
    public String addVege(@RequestBody VegeDemo vegeDemo) {
        vegeList.add(vegeDemo);
        return "蔬菜添加成功";
    }

    @PutMapping("/update")
    public String updateVege(@RequestBody VegeDemo vegeDemo) {
        for (int i = 0; i < vegeList.size(); i++) {
            if (vegeList.get(i).getvName().equals(vegeDemo.getvName())) {
                vegeList.set(i, vegeDemo);
                return "蔬菜信息更新成功";
            }
        }
        return "蔬菜不存在";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVege(@PathVariable Integer id) {
        vegeList.removeIf(vege -> vege.getvName().equals(id));
        return "蔬菜删除成功";
    }
}
