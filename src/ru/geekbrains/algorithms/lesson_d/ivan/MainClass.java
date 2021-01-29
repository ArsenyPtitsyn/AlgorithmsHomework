package ru.geekbrains.algorithms.lesson_d.ivan;

public class MainClass {
    public static void main(String[] args) {
        DoubleRelatedList<Cat> drl = new DoubleRelatedList<>();
        System.out.println(drl);
        drl.push(new Cat(1, "cat1"));
        drl.push(new Cat(2, "cat2"));
        drl.push(new Cat(3, "cat3"));
        System.out.println(drl);
        drl.pop();
        System.out.println(drl);
        MyIterator<Cat> mi = drl.getIterator();
        System.out.println(mi.getCurrent());
        System.out.println(mi.hasNext());
        mi.deleteCurrent();
        System.out.println(drl);
        drl.push(new Cat(4, "cat4"));
        System.out.println(drl);
        drl.delete(new Cat(4, "cat4"));
        System.out.println(drl);
    }
}
