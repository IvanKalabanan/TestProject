package com.ivacompany.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ivacompany.test.R;
import com.ivacompany.test.TestApp;
import com.ivacompany.test.interfaces.FragmentRequestListener;
import com.ivacompany.test.model.FileModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 27.12.16.
 */
public class FileRecyclerViewAdapter
        extends RecyclerView.Adapter<FileRecyclerViewAdapter.FileHolder> {

    private static final String TAG = "FileRecyclerViewAdapter";
    private final List<FileModel> fileList;
    private final FragmentRequestListener requestListener;
    private DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);

    public FileRecyclerViewAdapter(List<FileModel> fileList, FragmentRequestListener requestListener) {
        this.fileList = fileList;
        this.requestListener = requestListener;
    }

    @Override
    public FileRecyclerViewAdapter.FileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new FileHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(FileHolder holder, int position) {
        FileModel fileModel = fileList.get(position);
        holder.fileName.setText(fileModel.getFileName());
        holder.modifiedDate.setText(formatter.format(fileModel.getModDate()).toString());

        holder.fileName.append(fileModel.getFileType());

        switch (fileModel.getFileType()) {
            case ".png":
                holder.image.setImageResource(R.drawable.ic_image);
                break;
            case ".docx":
            case ".pdf":
                holder.image.setImageResource(R.drawable.ic_document);
                break;
            case "":
                holder.image.setImageResource(R.drawable.ic_folder);
                break;
        }

        if(!fileModel.isBlue() && !fileModel.isOrange()) {
            holder.space.setVisibility(View.VISIBLE);
        } else if (fileModel.isBlue() && !fileModel.isOrange()){
            holder.orangeLine.setVisibility(View.GONE);
        } else if (!fileModel.isBlue() && fileModel.isOrange()) {
            holder.blueLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }


    public class FileHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.blueLine) View blueLine;
        @BindView(R.id.orangeLine) View orangeLine;
        @BindView(R.id.space) View space;
        @BindView(R.id.image) ImageView image;
        @BindView(R.id.fileName) TextView fileName;
        @BindView(R.id.modifiedDate) TextView modifiedDate;


        public FileHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fileList.get(getAdapterPosition()).isFolder()){
                        requestListener.startMainFragment(fileList.get(getAdapterPosition()).getId());
                    } else {
                        Toast.makeText(
                                TestApp.getAppContext(),
                                fileList.get(getAdapterPosition()).getFileName(),
                                Toast.LENGTH_SHORT)
                                .show();
                        Log.d(TAG, "It is a File - " + fileList.get(getAdapterPosition()).getFileName());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                        requestListener.startDialog();
                    return false;
                }
            });
        }
    }

}
