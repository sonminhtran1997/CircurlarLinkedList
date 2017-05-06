/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs143;

/**
 *
 * @author sontran
 */
public class run {

    public static void main(String args[]) {
        EndlessList<Number> list = new EndlessList<>();

//        list.addPrev(1);
//        list.addPrev(2);
//        list.addPrev(3);
//        list.addPrev(4);

        list.addNext(1);
        list.addNext(2);
        list.addNext(3);
        list.addNext(4);
        System.out.println(list);

    }
}
