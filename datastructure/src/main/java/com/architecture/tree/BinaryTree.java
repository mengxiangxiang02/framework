package com.architecture.tree;

/**
 * @Auther: mengxiangxiang
 * @Date: 2019/1/16 16:27
 * @Description:二进制树操作
 */

class Node
{
    public int data;

    public Node leftNode;

    public Node rightNode;

    public void displayNode()
    {
        System.out.println('{');
        System.out.println(data);
        System.out.println('}');
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}

public class BinaryTree {
    private Node root;

    public BinaryTree()
    {
        root=null;
    }

    //find node with key 如果比当前节点小，则去该节点的左侧去找
    public Node find(int key)
    {
        Node current=root;
        while(current.data!=key)
        {
            if(key<current.data)
            {
                current=current.leftNode;
            }else
            {
                current=current.rightNode;
            }
            if(current==null) return null;
        }

        return current;
    }

    /**
     * 插入的时候判断当前节点和查找的节点大小，比当前节点小则放到节点的左边。
     * 如果节点的左子树为空，则挂到该节点的左侧
     * @param key
     */
    public void insert(int key)
    {
        Node newNode=new Node();
        newNode.data=10;

        if(root==null)
        {
            root=newNode;
            return;
        }else
        {
            Node current=root;
            Node parent=null;
            while(true)
            {
                parent=current;
                if(key<current.data)
                {
                    current=current.leftNode;
                    if(current==null)
                    {
                        parent.leftNode=newNode;
                        return;
                    }
                }else if(key>current.data)
                {
                    current=current.rightNode;
                    if(current==null)
                    {
                        parent.rightNode=newNode;
                    }

                }else {
                    return;
                }
            }
        }
    }

    /**
     * 删除节点，当没有子节点的时候，直接删除
     * 如果有子节点要从子节点找到一个节点替换该节点
     * 找替代节点的话一般找要删除节点的右节点的最左子节点
     * @param key
     */
    public boolean delete(int key)
    {
        Node current=root;//定义当前节点
        Node parent=root;//定义当前要循环节点的父节点

        boolean isleftChild=true;//定义是否是父节点的左节点还是右节点
        while (current.data!=key)
        {
            parent=current;
            if(key<current.data)
            {
                isleftChild=true;
                current=current.leftNode;
            }else
            {
                isleftChild=false;
                current=current.rightNode;
            }
            if(current==null) return false;
        }

        //found node to delete
        if(current.leftNode==null&&current.rightNode==null)
        {
            if(current==root) root=null;
            else if(isleftChild)
            {
                parent.leftNode=null;
            }else
            {
                parent.rightNode=null;
            }
        }else if(current.leftNode==null)
        {
            if(current==root) root=current.rightNode;
            else if(isleftChild) parent.leftNode=current.rightNode;
            else parent.rightNode=current.rightNode;
        }
        else if(current.rightNode==null)
        {
            if(current==root) root=current.leftNode;
            else if(isleftChild) parent.leftNode=current.leftNode;
            else parent.rightNode=current.leftNode;
        }else
        {
            //如果要删除的节点存在左右子节点，则查找替代节点，将要删除节点的父节点指向替代者
            Node successor=getSuccessor(current);
            if(current==root) root=successor;

            else if(isleftChild) parent.leftNode=successor;

            else parent.rightNode=successor;

            //将当前左节点赋值给替代者左节点
            successor.leftNode=current.leftNode;
        }

        return true;
    }

    private Node getSuccessor(Node delnode)
    {
        Node successorParent=delnode;//替代者的父节点
        Node successor=delnode;//替代者
        Node curent=delnode.rightNode;//go to right node
        while(curent!=null)
        {
            successorParent=successor;//保存替代者的父节点
            successor=curent;//将替代者下移
            curent=curent.leftNode;//当前节点下移
        }

        if(successor!=delnode.rightNode)
        {
            successorParent.leftNode=successor.rightNode;//将替代者的右节点挂到替代者父节点的左节点上
            successor.rightNode=delnode.rightNode;//将要删除的右节点付给替代者的右节点
        }
        return  successor;


    }
}
