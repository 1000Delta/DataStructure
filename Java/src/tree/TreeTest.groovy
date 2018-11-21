package tree

import common.Callback

class TreeTest {

    static class AddData implements Callback {

        @Override
        void solve(Object... objects) {

            for (File i : objects[1].listFiles()) {

                objects[0].setData(i.path)
                if (!Objects.isNull(i.list())) {

                    objects[0].setFirstChild('')
                    solve(objects[0].getFirstChild(), i)
                }
                objects[0].setNextSibling('')
                objects[0] = objects[0].getNextSibling()
            }
        }
    }

    static class rData implements Callback {

        @Override
        void solve(Object... objects) {

            System.out.println(objects[0].data)
        }
    }

    public static void main(String[] args) {

        Tree<String> fsTree = new Tree<>()
        fsTree.root.setData("/home/delta/Documents/A11N0tes")
        fsTree.root.setFirstChild('')
        File f = new File(fsTree.root.getData())
        Callback add = new AddData()
        Callback read = new rData()
        add.solve(fsTree.root.getFirstChild(), f)
        fsTree.preTraversal(read)
    }

    static void testPreTraversal() {


    }


}
