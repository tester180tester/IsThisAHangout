package com.example.isthisahangout.repository

import com.example.isthisahangout.models.Comments
import com.example.isthisahangout.utils.Resource
import com.example.isthisahangout.utils.asResourceFlow
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CommentsRepository @Inject constructor(
    @Named("CommentsRef") val commentsRef: CollectionReference
) {

    fun getPostComments(postId: String?): Flow<Resource<List<Comments>>> =
        postId?.let {
            commentsRef.document(postId).collection("comments").asResourceFlow {
                it.toObjects(Comments::class.java)
            }
        } ?: emptyFlow()

    fun getVideosComments(videotId: String?): Flow<Resource<List<Comments>>> =
        videotId?.let {
            commentsRef.document(videotId).collection("comments").asResourceFlow {
                it.toObjects(Comments::class.java)
            }
        } ?: emptyFlow()

}