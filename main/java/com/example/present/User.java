package com.example.present;

public class User {
    public String firstName;
    public String lastName;
    public String email;

    public User() {

    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

/*

protected void onStart() {
super.onStart();
FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions.Builder<Student>()
.setQuery(reference, Student.class)
.build();

FirebaseRecyclerAdapter<Student, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Student, MyViewHolder>(options){

@Override
protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @Nonnull Student student) {
holder.setName(student.getFirstName());
holder.setLastName(student.getSurName());
holder.setStudentNo(student.getStudentNo());
holder.setDOB(student.getDateofBirth());
}


@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student, parent, false);
return new MyViewHolder(view);
}


};

recyclerView.setAdapter(adapter);
adapter.startListening();

}








public static class MyViewHolder extends RecyclerView.ViewHolder{
View mView;

public MyViewHolder(@NonNull View itemView) {
super(itemView);
mView = itemView;


}

public void setName(String name) {
TextView nameTextView = mView.findViewById(R.id.tvForeName);
nameTextView.setText(name);
}

public void setLastName(String lastName){
TextView lastNameTextView = mView.findViewById(R.id.tvLastName);
lastNameTextView.setText(lastName);
}

public void setStudentNo(String stuNo){
TextView tvStudentNo = mView.findViewById(R.id.tvStudentNo);
tvStudentNo.setText(stuNo);
}

public void setDOB(String birthDate){
TextView tvDOB = mView.findViewById(R.id.tvDOB);
tvDOB.setText(birthDate);
}


}
 */
